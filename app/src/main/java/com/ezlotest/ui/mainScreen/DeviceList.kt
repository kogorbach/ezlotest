package com.ezlotest.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ezlotest.R
import com.ezlotest.ui.model.UiDeviceModel

@Composable
fun DeviceList(
    modifier: Modifier = Modifier,
    devices: List<UiDeviceModel> = emptyList()
) {
    LazyColumn(
        modifier = modifier.background(Color.White)
    ) {
        devices.forEachIndexed { index, device ->
            item {
                DeviceListItem(index, device)
                Divider(
                    thickness = 2.dp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun DeviceListItem(index: Int, device: UiDeviceModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = device.iconResource),
            contentDescription = stringResource(id = R.string.detailScreenItemImageDescription),
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray, RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = device.title ?: stringResource(id = R.string.deviceMockTitle, index + 1),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = stringResource(id = R.string.deviceItemSerialNumber, device.serialNumber),
                fontSize = 12.sp
            )
        }
        Icon(
            painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
            contentDescription = "Arrow Icon",
            tint = Color.Gray
        )
    }
}

@Preview
@Composable
private fun DeviceListPreview() {
    DeviceList(
        devices = listOf(
            UiDeviceModel(
                title = "Home number 1",
                serialNumber = 54315343,
                iconResource = R.drawable.vera_secure_big
            ),

            UiDeviceModel(
                title = "Home number 1",
                serialNumber = 54315343,
                iconResource = R.drawable.vera_secure_big
            ),

            UiDeviceModel(
                title = "Home number 1",
                serialNumber = 54315343,
                iconResource = R.drawable.vera_secure_big
            )
        )
    )
}