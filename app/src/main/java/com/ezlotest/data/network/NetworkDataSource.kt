package com.ezlotest.data.network

import com.ezlotest.data.network.model.DeviceModel
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun fetchDevices(): List<DeviceModel> {
        return apiService.getData().devices
    }
}
