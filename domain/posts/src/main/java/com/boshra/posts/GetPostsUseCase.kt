package com.boshra.posts

import com.boshra.league.model.GeneralError
import com.boshra.league.model.NetworkResult
import com.boshra.posts.model.PostUiModel
import kotlinx.coroutines.flow.Flow

interface GetPostsUseCase {
  operator fun invoke(): Flow<NetworkResult<List<PostUiModel>, GeneralError>>
}
