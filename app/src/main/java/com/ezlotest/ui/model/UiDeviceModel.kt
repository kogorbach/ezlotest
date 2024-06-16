package com.ezlotest.ui.model

import androidx.annotation.DrawableRes
import com.ezlotest.R
import kotlinx.serialization.Serializable

@Serializable
data class UiDeviceModel(
    val title: String?,
    val serialNumber: Long,
    @DrawableRes val iconResource: Int
)