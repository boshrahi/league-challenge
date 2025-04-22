package com.boshra.league.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LeagueErrorResponse(
  @SerialName("status_code")
  val statusCode: Int,
  @SerialName("status_message")
  val statusMessage: String,
)
