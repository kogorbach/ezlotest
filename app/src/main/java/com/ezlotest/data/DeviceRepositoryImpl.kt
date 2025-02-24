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

    override suspend fun updateDeviceTitle(deviceId: Long, newTitle: String) {
        val device = deviceDao.getDeviceById(deviceId)
        device?.let {
            val updatedDevice = it.copy(title = newTitle)
            deviceDao.updateDevice(updatedDevice)
        }
    }

    override suspend fun removeDeviceById(deviceId: Long): List<DeviceModel> {
        deviceDao.deleteDeviceById(deviceId)
        return deviceDao.getDevices()
    }
}

