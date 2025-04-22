package com.boshra.league.api

import com.boshra.league.data.network.response.LeaguePostResultResponse
import com.boshra.league.data.network.response.LeagueUserResultResponse
import com.boshra.league.model.Post
import com.boshra.league.model.User

fun LeagueUserResultResponse.toUser(): User = User(
  username = this.username,
  avatarUrl = this.avatar,
  id = this.id.toString(),
)

fun LeaguePostResultResponse.toPost(): Post = Post(
  title = this.title,
  description = this.body,
  userID = this.userID.toString(),
)
