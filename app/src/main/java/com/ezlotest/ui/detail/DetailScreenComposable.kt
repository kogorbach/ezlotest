package com.ezlotest.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ezlotest.R
import com.ezlotest.ui.MainViewModel
import com.ezlotest.ui.common.ProfileHeader
import com.ezlotest.ui.model.UiDeviceModel

@Composable
fun DetailScreenComposable(
    modifier: Modifier = Modifier,
    deviceId: Long,
    viewModel: MainViewModel = hiltViewModel()
) {
    val indexedDevice = viewModel.getIndexedDeviceById(deviceId)
    Column(modifier = modifier) {
        ProfileHeader()
        indexedDevice.first?.let {
            DeviceDetails(device = it, index = indexedDevice.second)
        } // todo else draw mockup [by kostyan]
    }
}

@Composable
fun DeviceDetails(device: UiDeviceModel, index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = device.iconResource),
            contentDescription = "Device Image",
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = device.title ?: stringResource(
                    id = R.string.mainScreenItemHomeNumber,
                    index + 1
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "SN: ${device.macAddress}",
                fontSize = 14.sp
            )
            Text(
                text = "MAC Address: ${device.macAddress}",
                fontSize = 14.sp
            )
            Text(
                text = "Firmware: ${device.firmware}",
                fontSize = 14.sp
            )
            Text(
                text = "Model: ${device.model}",
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
private fun DetailsComposablePreview() {
    DetailScreenComposable(
        deviceId = 35112
    )
}
@Preview(showBackground = true)
@Composable
fun DeviceDetailsPreview() {
    val sampleDevice = UiDeviceModel(
        serialNumber = 45013855,
        macAddress = "e0:60:66:02:e2:1b",
        title = "Home Number 1",
        model = "Vera Edge",
        iconResource = R.drawable.vera_edge_big,
        firmware = "1.7.455",
    )
    DeviceDetails(device = sampleDevice, 1)
}