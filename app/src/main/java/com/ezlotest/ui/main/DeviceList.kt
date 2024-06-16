package com.ezlotest.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
    devices: List<UiDeviceModel> = emptyList(),
    onDeviceClick: (Long) -> Unit
) {
    LazyColumn(
        modifier = modifier.background(Color.White)
    ) {
        devices.forEachIndexed { index, device ->
            item {
                DeviceListItem(index, device, onDeviceClick)
                Divider(
                    thickness = 2.dp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun DeviceListItem(index: Int, device: UiDeviceModel, onDeviceClick: (Long) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onDeviceClick(device.serialNumber)
            }
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
        onDeviceClick = {},
        devices = listOf(
            UiDeviceModel(
                serialNumber = 45013855,
                macAddress = "e0:60:66:02:e2:1b",
                title = "Home Number 1",
                model = "Vera Edge",
                iconResource = R.drawable.vera_edge_big,
                firmware = "1.7.455",
            ),

            UiDeviceModel(
                serialNumber = 45013855,
                macAddress = "e0:60:66:02:e2:1b",
                title = "Home Number 1",
                model = "Vera Edge",
                iconResource = R.drawable.vera_edge_big,
                firmware = "1.7.455",
            ),

            UiDeviceModel(
                serialNumber = 45013855,
                macAddress = "e0:60:66:02:e2:1b",
                title = "Home Number 1",
                model = "Vera Edge",
                iconResource = R.drawable.vera_edge_big,
                firmware = "1.7.455",
            )
        )
    )
}