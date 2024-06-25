package com.alisys.androidar.di

import com.alisys.androidar.data.RequestAPIRetrofit
import com.alisys.androidar.data.RestAPI
import com.alisys.androidar.domain.IRepository
import com.alisys.androidar.domain.Repository
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