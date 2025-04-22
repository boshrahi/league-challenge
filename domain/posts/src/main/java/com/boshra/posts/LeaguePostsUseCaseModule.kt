package com.boshra.posts

import com.boshra.posts.impl.GetPostsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LeaguePostsUseCaseModule {

  @Binds
  abstract fun bindGetPostsUseCase(
    impl: GetPostsUseCaseImpl,
  ): GetPostsUseCase
}
