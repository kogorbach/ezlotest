package com.ezlotest.data.network

data class DeviceModel(
    val pk_Device: Long,
    val macAddress: String,
    val pK_DeviceType: Int,
    val pK_DeviceSubType: Int,
    val firmware: String,
    val server_Device: String,
    val server_Event: String,
    val server_Account: String,
    val internalIP: String,
    val lastAliveReported: String,
    val platform: String
)