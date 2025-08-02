package com.boshra.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boshra.league.domain.model.GeneralError
import com.boshra.league.domain.model.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
  private val getPostsUseCase: GetPostsUseCase,
) : ViewModel() {

  private val _state = MutableStateFlow(PostsUiState(isLoading = false))
  val state = _state.asStateFlow()

  fun getPosts() {
    viewModelScope.launch {
      getPostsUseCase().onStart {
        _state.update { state ->
          state.copy(
            isLoading = true,
            error = null,
            message = null,
            tryAgainFunc = null,
          )
        }
      }.onCompletion {
        _state.update { state -> state.copy(isLoading = false) }
      }.onEach { result ->
        when (result) {
          is NetworkResult.Success -> {
            _state.update { state ->
              state.copy(
                postsSections = state.postsSections + result.data,
              )
            }
          }

          is NetworkResult.Failure -> {
            when (result.error) {
              is GeneralError.ApiError -> {
                _state.update { state ->
                  state.copy(
                    error = result.error,
                    message = (result.error as GeneralError.ApiError).message,
                    isLoading = false,
                    tryAgainFunc = ::getPosts,
                  )
                }
              }

              GeneralError.NetworkError -> {
                _state.update { state ->
                  state.copy(
                    error = result.error,
                    message = "No internet connection. Please check your network settings.",
                    isLoading = false,
                    tryAgainFunc = ::getPosts,
                  )
                }
              }

              is GeneralError.UnknownError -> {
                _state.update { state ->
                  state.copy(
                    error = result.error,
                    message = (result.error as GeneralError.UnknownError).error.message,
                    isLoading = false,
                    tryAgainFunc = ::getPosts,
                  )
                }
              }
            }
          }
        }
      }.collect()
    }
  }
}
