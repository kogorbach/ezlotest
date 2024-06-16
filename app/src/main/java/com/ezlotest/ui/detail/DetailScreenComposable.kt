package com.ezlotest.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ezlotest.ui.MainViewModel
import com.ezlotest.ui.common.ProfileHeader
import com.ezlotest.ui.model.UiDeviceModel

@Composable
fun DetailScreenComposable(
    modifier: Modifier = Modifier,
    deviceId: Long,
    viewModel: MainViewModel = hiltViewModel()
) {
    val device = viewModel.getDeviceById(deviceId)
    Column(modifier = modifier) {
        ProfileHeader()
        DeviceDetails(device = device)
    }
}

@Composable
fun DeviceDetails(modifier: Modifier = Modifier, device: UiDeviceModel?) {

}