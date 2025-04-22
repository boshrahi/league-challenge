package com.boshra.league.data.auth

import com.boshra.league.data.auth.AuthRepository
import com.boshra.league.data.auth.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AuthModule {

  @Binds
  abstract fun providesAuthRemoteSource(impl: AuthRepositoryImpl): AuthRepository
}
