package com.boshra.league.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeaguePostResultResponse(
  @SerialName("userId")
  val userID: Long,
  val id: Long,
  val title: String,
  val body: String,
)
