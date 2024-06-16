package com.ezlotest.data

import com.ezlotest.data.local.DeviceDao
import com.ezlotest.data.network.NetworkDataSource
import com.ezlotest.data.network.model.DeviceModel
import com.ezlotest.domain.DeviceRepository
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val deviceDao: DeviceDao
) : DeviceRepository {

    override suspend fun fetchDevices(): List<DeviceModel> {
        return if (deviceDao.getCount() == 0) {
            return updateDevices()
        } else {
            deviceDao.getDevices()
        }
    }

    override suspend fun updateDevices(): List<DeviceModel> {
        networkDataSource.fetchDevices().also { devices ->
            deviceDao.clearAll()
            deviceDao.insertDevices(devices)
            return devices
        }
    }
}

