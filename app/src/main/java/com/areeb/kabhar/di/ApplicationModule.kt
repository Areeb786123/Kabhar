package com.areeb.kabhar.di

import com.areeb.kabhar.data.network.ApiConstants
import com.areeb.kabhar.data.network.RemoteDataSource
import com.areeb.kabhar.data.network.remote.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun providesNewsApi(remoteDataSource: RemoteDataSource): NewsApi {
        return remoteDataSource.buildApi(ApiConstants.BASE_URL, NewsApi::class.java)
    }
}
