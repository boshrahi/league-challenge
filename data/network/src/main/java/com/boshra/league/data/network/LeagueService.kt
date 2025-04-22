package com.boshra.league.data.network

import com.boshra.league.data.network.adapter.NetworkResponse
import com.boshra.league.data.network.response.LeagueErrorResponse
import com.boshra.league.data.network.response.LeagueLoginResponse
import com.boshra.league.data.network.response.LeaguePostResultResponse
import com.boshra.league.data.network.response.LeagueUserResultResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface LeagueService {

  @GET("challenge/api/login")
  suspend fun login(): NetworkResponse<LeagueLoginResponse, LeagueErrorResponse>

  @GET("challenge/api/users")
  suspend fun getUsers(
    @Header("x-access-token") apiKey: String,
  ): NetworkResponse<List<LeagueUserResultResponse>, LeagueErrorResponse>

  @GET("challenge/api/posts")
  suspend fun getPosts(
    @Header("x-access-token") apiKey: String,
  ): NetworkResponse<List<LeaguePostResultResponse>, LeagueErrorResponse>
}
