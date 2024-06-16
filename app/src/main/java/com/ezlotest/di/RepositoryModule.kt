package com.ezlotest.di

import com.ezlotest.data.DeviceRepositoryImpl
import com.ezlotest.data.local.DeviceDao
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

    @Provides
    @Singleton
    fun provideDeviceRepository(
        networkDataSource: NetworkDataSource,
        deviceDao: DeviceDao
    ): DeviceRepository {
        return DeviceRepositoryImpl(
            networkDataSource,
            deviceDao
        )
    }
}
