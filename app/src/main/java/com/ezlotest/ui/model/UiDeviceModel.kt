package com.ezlotest.ui.model

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class UiDeviceModel(
    val title: String,
    val serialNumber: Long,
    val firmware: String,
    val macAddress: String,
    val model: String,
    @DrawableRes val iconResource: Int
)