package com.ezlotest.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "devices")
data class DeviceModel(
    val title: String = "",
    @PrimaryKey @SerializedName("PK_Device") val pkDevice: Long,
    @SerializedName("MacAddress") val macAddress: String,
    @SerializedName("PK_DeviceType") val pkDeviceType: Int,
    @SerializedName("PK_DeviceSubType") val pkDeviceSubType: Int,
    @SerializedName("Firmware") val firmware: String,
    @SerializedName("Server_Device") val serverDevice: String,
    @SerializedName("Server_Event") val serverEvent: String,
    @SerializedName("Server_Account") val serverAccount: String,
    @SerializedName("InternalIP") val internalIP: String,
    @SerializedName("LastAliveReported") val lastAliveReported: String,
    @SerializedName("Platform") val platform: String
)