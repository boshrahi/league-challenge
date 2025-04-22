package com.boshra.league.api

import com.boshra.league.model.GeneralError
import com.boshra.league.model.NetworkResult
import com.boshra.league.model.Post
import com.boshra.league.model.TokenHolder
import com.boshra.league.model.User

interface LeagueRemoteSource {

  suspend fun getUsers(token: String): NetworkResult<List<User>, GeneralError>
  suspend fun getPosts(token: String): NetworkResult<List<Post>, GeneralError>
  suspend fun login(): NetworkResult<TokenHolder, GeneralError>
}
