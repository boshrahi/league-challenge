package com.boshra.league.data.auth

import com.boshra.league.api.LeagueRemoteSource
import com.boshra.league.model.GeneralError
import com.boshra.league.model.NetworkResult
import com.boshra.league.model.TokenHolder
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val leagueRemoteSource: LeagueRemoteSource,
) : AuthRepository {

  override suspend fun login(): NetworkResult<TokenHolder, GeneralError> {
    return leagueRemoteSource.login()
  }
}
