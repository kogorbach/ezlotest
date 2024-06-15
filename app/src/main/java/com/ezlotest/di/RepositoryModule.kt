package com.ezlotest.di

import com.ezlotest.data.DeviceRepositoryImpl
import com.ezlotest.data.network.NetworkDataSource
import com.ezlotest.domain.DeviceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // todo add local source with Room [by kostyan]
    @Provides
    @Singleton
    fun provideDeviceRepository(
        networkDataSource: NetworkDataSource,
//        localDataSource: LocalDataSource
    ): DeviceRepository {
        return DeviceRepositoryImpl(
            networkDataSource,
//            localDataSource
        )
    }
}
