package com.boshra.league.api

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ApiModule {

  @Binds
  abstract fun bindLeagueService(impl: LeagueRemoteSourceImpl): LeagueRemoteSource
}
