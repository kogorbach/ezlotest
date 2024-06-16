package com.ezlotest.ui.detail

import kotlinx.serialization.Serializable

@Serializable
data class DetailScreen(
    val deviceId: Long,
    val editMode: Boolean = false
)
