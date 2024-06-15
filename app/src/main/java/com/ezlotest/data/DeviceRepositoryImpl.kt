package com.ezlotest.data

import com.ezlotest.data.network.NetworkDataSource
import com.ezlotest.data.network.model.DeviceModel
import com.ezlotest.domain.DeviceRepository
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
//    private val localDataSource: LocalDeviceDataSource // todo implement with Room [by kostyan]
) : DeviceRepository {

    override suspend fun fetchDevices(): List<DeviceModel> {
//        return if (localDataSource.isEmpty()) {
            return updateDevices()
//        } else {
//            localDataSource.getDevices()
//        }
    }

    override suspend fun updateDevices(): List<DeviceModel> {
        networkDataSource.fetchDevices().also { devices ->
//            localDataSource.saveDevices(devices)
            return devices
        }
    }
}

