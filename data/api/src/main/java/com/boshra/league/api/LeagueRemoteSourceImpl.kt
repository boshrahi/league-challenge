package com.boshra.league.api

import com.boshra.league.data.network.LeagueService
import com.boshra.league.data.network.adapter.NetworkResponse
import com.boshra.league.domain.model.GeneralError
import com.boshra.league.domain.model.NetworkResult
import com.boshra.league.domain.model.Post
import com.boshra.league.domain.model.TokenHolder
import com.boshra.league.domain.model.User
import javax.inject.Inject

internal class LeagueRemoteSourceImpl @Inject constructor(
  private val leagueService: LeagueService,
) : LeagueRemoteSource {
  override suspend fun getUsers(token: String): NetworkResult<List<User>, GeneralError> =
    when (
      val result = leagueService.getUsers(
        apiKey = token,
      )
    ) {
      is NetworkResponse.Success -> {
        val userResponse = result.body
        if (userResponse == null) {
          NetworkResult.Failure(GeneralError.UnknownError(Throwable("user list response is null")))
        } else {
          NetworkResult.Success(userResponse.map { it.toUser() })
        }
      }

      is NetworkResponse.ApiError -> {
        val errorResponse = result.body
        NetworkResult.Failure(
          GeneralError.ApiError(
            errorResponse.statusMessage,
            errorResponse.statusCode,
          ),
        )
      }

      is NetworkResponse.NetworkError -> NetworkResult.Failure(GeneralError.NetworkError)
      is NetworkResponse.UnknownError -> NetworkResult.Failure(GeneralError.UnknownError(result.error))
    }

  override suspend fun getPosts(token: String): NetworkResult<List<Post>, GeneralError> =
    when (
      val result = leagueService.getPosts(
        apiKey = token,
      )
    ) {
      is NetworkResponse.Success -> {
        val postResponse = result.body
        if (postResponse == null) {
          NetworkResult.Failure(GeneralError.UnknownError(Throwable("user list response is null")))
        } else {
          NetworkResult.Success(postResponse.map { it.toPost() })
        }
      }

      is NetworkResponse.ApiError -> {
        val errorResponse = result.body
        NetworkResult.Failure(
          GeneralError.ApiError(
            errorResponse.statusMessage,
            errorResponse.statusCode,
          ),
        )
      }

      is NetworkResponse.NetworkError -> NetworkResult.Failure(GeneralError.NetworkError)
      is NetworkResponse.UnknownError -> NetworkResult.Failure(GeneralError.UnknownError(result.error))
    }

  override suspend fun login(): NetworkResult<TokenHolder, GeneralError> =
    when (val result = leagueService.login()) {
      is NetworkResponse.Success -> {
        val postResponse = result.body
        val token = postResponse?.apiKey
        if (postResponse == null) {
          NetworkResult.Failure(GeneralError.UnknownError(Throwable("user list response is null")))
        } else if (token == null) {
          NetworkResult.Failure(GeneralError.UnknownError(Throwable("token is null")))
        } else {
          NetworkResult.Success(TokenHolder(token))
        }
      }

      is NetworkResponse.ApiError -> {
        val errorResponse = result.body
        NetworkResult.Failure(
          GeneralError.ApiError(
            errorResponse.statusMessage,
            errorResponse.statusCode,
          ),
        )
      }

      is NetworkResponse.NetworkError -> NetworkResult.Failure(GeneralError.NetworkError)
      is NetworkResponse.UnknownError -> NetworkResult.Failure(GeneralError.UnknownError(result.error))
    }
}
