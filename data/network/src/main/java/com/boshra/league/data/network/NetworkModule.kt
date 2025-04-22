package com.boshra.league.data.network

import com.boshra.league.data.network.adapter.NetworkCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.CallAdapter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun providesJson(): Json {
    return Json { ignoreUnknownKeys = true }
  }

  @Provides
  @Singleton
  fun providesRetrofit(
    json: Json,
    networkCallAdapterFactory: CallAdapter.Factory,
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://engineering.league.dev/")
      .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
      .addCallAdapterFactory(networkCallAdapterFactory)
      .build()
  }

  @Provides
  @Singleton
  fun providesCustomService(retrofit: Retrofit): LeagueService {
    return retrofit.create(LeagueService::class.java)
  }

  @Provides
  @Singleton
  fun providesNetworkCallAdapterFactory(): CallAdapter.Factory {
    return NetworkCallAdapterFactory()
  }
}
