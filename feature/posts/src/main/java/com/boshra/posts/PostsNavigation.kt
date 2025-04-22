package com.boshra.posts

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.postsScreen() {
  composable("posts") {
    PostsScreen(viewModel = hiltViewModel())
  }
}
