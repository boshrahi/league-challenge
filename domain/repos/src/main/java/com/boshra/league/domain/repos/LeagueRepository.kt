package com.boshra.league.domain.repos

import com.boshra.league.domain.model.GeneralError
import com.boshra.league.domain.model.NetworkResult
import com.boshra.league.domain.model.Post
import com.boshra.league.domain.model.User

interface LeagueRepository {

  suspend fun getPosts(token: String): NetworkResult<List<Post>, GeneralError>
  suspend fun getUsers(token: String): NetworkResult<List<User>, GeneralError>
}
