package com.boshra.league.posts

import com.boshra.league.api.LeagueRemoteSource
import com.boshra.league.domain.model.GeneralError
import com.boshra.league.domain.model.NetworkResult
import com.boshra.league.domain.model.Post
import com.boshra.league.domain.model.User
import com.boshra.league.domain.repos.LeagueRepository
import javax.inject.Inject

internal class LeagueRepositoryImpl @Inject constructor(
  private val leagueRemoteSource: LeagueRemoteSource,
) : LeagueRepository {

  override suspend fun getPosts(token: String): NetworkResult<List<Post>, GeneralError> =
    leagueRemoteSource.getPosts(token)

  override suspend fun getUsers(token: String): NetworkResult<List<User>, GeneralError> =
    leagueRemoteSource.getUsers(token)
}
