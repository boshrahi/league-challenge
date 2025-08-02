package com.boshra.league.data.auth

import com.boshra.league.api.LeagueRemoteSource
import com.boshra.league.domain.model.GeneralError
import com.boshra.league.domain.model.NetworkResult
import com.boshra.league.domain.model.TokenHolder
import com.boshra.league.domain.repos.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
  private val leagueRemoteSource: LeagueRemoteSource,
) : AuthRepository {

  override suspend fun login(): NetworkResult<TokenHolder, GeneralError> {
    return leagueRemoteSource.login()
  }
}
