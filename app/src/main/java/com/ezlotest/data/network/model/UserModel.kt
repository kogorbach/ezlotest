package com.ezlotest.data.network.model

data class DeviceModel(
    val pkDevice: Long,
    val macAddress: String,
    val pkDevicetype: Int,
    val pkDevicesubtype: Int,
    val firmware: String,
    val serverDevice: String,
    val serverEvent: String,
    val serverAccount: String,
    val internalIP: String,
    val lastAliveReported: String,
    val platform: String
)