package com.boshra.league.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueLoginResponse(
  @SerialName("api_key")
  val apiKey: String? = null,
)
