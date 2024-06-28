package com.androidar.example.di

import com.androidar.example.data.RequestAPIRetrofit
import com.androidar.example.data.RestAPI
import com.androidar.example.domain.IRepository
import com.androidar.example.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAPI(): RestAPI {
        return RequestAPIRetrofit()
    }


    @Provides
    @Singleton
    fun provideRepository(restAPI: RestAPI): IRepository {
        return Repository(restAPI)
    }
}