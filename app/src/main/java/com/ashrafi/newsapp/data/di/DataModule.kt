package com.ashrafi.newsapp.data.di

import com.ashrafi.newsapp.data.repository.NewsRepositoryImpl
import com.ashrafi.newsapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository
}