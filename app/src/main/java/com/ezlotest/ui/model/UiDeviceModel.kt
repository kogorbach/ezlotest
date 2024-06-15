package com.ezlotest.ui.model

import androidx.annotation.DrawableRes

data class UiDeviceModel(
    val title: String,
    val serialNumber: Long,
    @DrawableRes val iconResource: Int
)
