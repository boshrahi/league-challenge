package com.boshra.league.posts

import com.boshra.league.model.GeneralError
import com.boshra.league.model.NetworkResult
import com.boshra.league.model.Post
import com.boshra.league.model.User

interface LeagueRepository {

  suspend fun getPosts(token: String): NetworkResult<List<Post>, GeneralError>
  suspend fun getUsers(token: String): NetworkResult<List<User>, GeneralError>
}
