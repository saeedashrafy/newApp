package com.ashrafi.newsapp.domain.di

import com.ashrafi.newsapp.domain.useCase.GetNewsUseCase
import com.ashrafi.newsapp.domain.useCase.GetNewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun provideGetNewsUseCase(getNewsUseCase: GetNewsUseCaseImpl): GetNewsUseCase

}