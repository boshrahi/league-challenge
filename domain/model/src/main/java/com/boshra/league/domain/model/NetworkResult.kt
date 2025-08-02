package com.boshra.league.domain.model

sealed class NetworkResult<out S, out E> {
  data class Success<out S>(val data: S) : NetworkResult<S, Nothing>()
  data class Failure<out E>(val error: E) : NetworkResult<Nothing, E>()
}
