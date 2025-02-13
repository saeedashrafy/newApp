package com.ashrafi.newsapp.domain.di

import com.ashrafi.newsapp.domain.useCase.NewsListUseCase
import com.ashrafi.newsapp.domain.useCase.NewsListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun provideGetNewsUseCase(getNewsUseCase: NewsListUseCaseImpl): NewsListUseCase

}