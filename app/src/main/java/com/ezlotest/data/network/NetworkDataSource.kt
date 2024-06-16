package com.ezlotest.data.network

import com.ezlotest.data.network.model.DeviceModel
import com.ezlotest.utils.Constants
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun fetchDevices(): List<DeviceModel> {
        return apiService.getData().devices.sortedBy { it.pkDevice }.mapIndexed { index, device ->
            device.copy(title = "${Constants.DEVICE_MOCK_TITLE} ${index + 1}")
        }
    }
}
