package com.ashrafi.newsapp.remote.di

import com.ashrafi.newsapp.data.source.remote.NewsRemoteDataSource
import com.ashrafi.newsapp.remote.service.ApiService
import com.ashrafi.newsapp.remote.service.interceptor.Interceptor
import com.ashrafi.newsapp.remote.source.BaseRemoteDataSource
import com.ashrafi.newsapp.remote.source.BaseRemoteDataSourceImpl
import com.ashrafi.newsapp.remote.source.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(
                okHttpClient
            ).build()
    }

    @Singleton
    @Provides
    fun provideOkhttp(
        networkHeaderInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkHeaderInterceptor)
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        apiService: ApiService,
        baseRemoteDataSource: BaseRemoteDataSource,
    ): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(
            apiService = apiService,
            baseRemoteDataSource = baseRemoteDataSource
        )
    }

    @Singleton
    @Provides
    fun provideBaseRemoteDataSource(
        baseRemoteDataSourceImpl: BaseRemoteDataSourceImpl
    ): BaseRemoteDataSource {
        return BaseRemoteDataSourceImpl()
    }

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "1a7eb879f601484b9f4a8bca111c9997"
    }
}
