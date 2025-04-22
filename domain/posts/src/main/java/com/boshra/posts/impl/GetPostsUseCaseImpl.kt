package com.boshra.posts.impl

import com.boshra.league.data.auth.AuthRepository
import com.boshra.league.model.GeneralError
import com.boshra.league.model.NetworkResult
import com.boshra.league.model.TokenHolder
import com.boshra.league.posts.LeagueRepository
import com.boshra.posts.GetPostsUseCase
import com.boshra.posts.model.PostUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostsUseCaseImpl @Inject constructor(
  private val authRepository: AuthRepository,
  private val leagueRepository: LeagueRepository,
) : GetPostsUseCase {
  override fun invoke(): Flow<NetworkResult<List<PostUiModel>, GeneralError>> =
    flow {
      when (val res = authRepository.login()) {
        is NetworkResult.Failure -> {
          emit(NetworkResult.Failure(res.error))
        }

        is NetworkResult.Success -> {
          getUsersAndPosts(res.data)
        }
      }
    }

  private suspend fun FlowCollector<NetworkResult<List<PostUiModel>, GeneralError>>.getUsersAndPosts(
    token: TokenHolder,
  ) {
    val postRes = leagueRepository.getPosts(token.apiToken ?: "")
    val userRes = leagueRepository.getUsers(token.apiToken ?: "")

    if (postRes is NetworkResult.Success && userRes is NetworkResult.Success) {
      val postsUi = postRes.data.map { post ->
        val extractedUser = userRes.data.find { user -> user.id == post.userID }
        PostUiModel(
          title = post.title,
          description = post.description,
          avatarUrl = extractedUser?.avatarUrl,
          username = extractedUser?.username,
        )
      }
      emit(NetworkResult.Success(postsUi))
    } else if (postRes is NetworkResult.Failure) {
      emit(postRes)
    } else if (userRes is NetworkResult.Failure) {
      emit(userRes)
    }
  }
}
