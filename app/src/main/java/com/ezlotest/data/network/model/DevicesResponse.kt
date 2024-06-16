package com.ezlotest.data.network.model

import com.google.gson.annotations.SerializedName

data class DevicesResponse (
   @SerializedName("Devices") val devices: List<DeviceModel>
)