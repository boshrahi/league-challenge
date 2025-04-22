package com.boshra.league.data.auth

import com.boshra.league.model.TokenHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StaticModule {

  @Provides
  @Singleton
  fun providesTokenHolder(): TokenHolder = TokenHolder()
}
