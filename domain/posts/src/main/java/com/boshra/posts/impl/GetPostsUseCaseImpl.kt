package com.boshra.posts.impl

import com.boshra.league.domain.model.GeneralError
import com.boshra.league.domain.model.NetworkResult
import com.boshra.league.domain.model.TokenHolder
import com.boshra.league.domain.repos.AuthRepository
import com.boshra.league.domain.repos.LeagueRepository
import com.boshra.posts.GetPostsUseCase
import com.boshra.posts.model.PostUiModel
import kotlinx.coroutines.flow.Flow
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
          emit(getUsersAndPosts(res.data))
        }
      }
    }

  private suspend fun getUsersAndPosts(
    token: TokenHolder,
  ): NetworkResult<List<PostUiModel>, GeneralError> {
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
      return NetworkResult.Success(postsUi)
    } else if (postRes is NetworkResult.Failure) {
      return postRes
    } else if (userRes is NetworkResult.Failure) {
      return userRes
    } else {
      return NetworkResult.Failure(GeneralError.UnknownError(Throwable("unknown")))
    }
  }
}
