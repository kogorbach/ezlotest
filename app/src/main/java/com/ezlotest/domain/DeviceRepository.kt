package com.ezlotest.domain

import com.ezlotest.data.network.model.DeviceModel

interface DeviceRepository {
    suspend fun fetchDevices(): List<DeviceModel>
    suspend fun updateDevices(): List<DeviceModel>
}
