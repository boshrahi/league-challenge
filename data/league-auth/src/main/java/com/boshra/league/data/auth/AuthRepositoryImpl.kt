package com.boshra.league.data.auth

import com.boshra.league.api.LeagueRemoteSource
import com.boshra.league.domain.model.GeneralError
import com.boshra.league.domain.model.NetworkResult
import com.boshra.league.domain.model.TokenHolder
import com.boshra.league.domain.repos.AuthRepository
import com.boshra.storage.SecureTokenStorage
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
  private val leagueRemoteSource: LeagueRemoteSource,
  private val storage: SecureTokenStorage,
) : AuthRepository {

  override suspend fun login(): NetworkResult<TokenHolder, GeneralError> {
    val currentToken = storage.getAccessToken()
    if (currentToken.isNullOrEmpty()) {
      when (val result = leagueRemoteSource.login()) {
        is NetworkResult.Failure<Any> -> {
          return result
        }
        is NetworkResult.Success<TokenHolder> -> {
          result.data.apiToken?.let {
            storage.saveAccessToken(it)
          }
          return result
        }
      }
    } else {
      return NetworkResult.Success(TokenHolder(currentToken))
    }
  }
}
