package com.boshra.league.posts

import com.boshra.league.domain.repos.LeagueRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LeaguePostsModule {

  @Binds
  abstract fun providesLeaguePostsRemoteSource(impl: LeagueRepositoryImpl): LeagueRepository
}
