package com.boshra.posts

import com.boshra.league.domain.model.GeneralError
import com.boshra.posts.model.PostUiModel

data class PostsUiState(
  val isLoading: Boolean,
  val postsSections: List<PostUiModel> = emptyList(),
  // these fields for Error States of Posts Ui
  val message: String? = null,
  val error: GeneralError? = null,
  val tryAgainFunc: (() -> Unit)? = null,
)
