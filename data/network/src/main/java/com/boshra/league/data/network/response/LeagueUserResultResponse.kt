package com.boshra.league.data.network.response

import kotlinx.serialization.Serializable

@Serializable
data class LeagueUserResultResponse(
  val id: Long? = null,
  val avatar: String? = null,
  val name: String? = null,
  val username: String? = null,
  val email: String? = null,
  val phone: String? = null,
  val website: String? = null,
)
