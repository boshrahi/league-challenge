package com.boshra.storage

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

  @Provides
  @Singleton
  fun provideSecureTokenStorage(
    @ApplicationContext applicationContext: Context,
  ): SecureTokenStorage {
    return SecureTokenStorage(applicationContext)
  }
}
