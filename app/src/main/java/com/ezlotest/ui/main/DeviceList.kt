package com.ezlotest.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
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
    onDeviceClick: (Long) -> Unit = {},
    onEditClick: (Long) -> Unit = {},
    onRemoveDevice: (Long) -> Unit = {}
) {
    val showDialog = remember { mutableStateOf(false) }
    val selectedDevice = remember { mutableStateOf<UiDeviceModel?>(null) }

    LazyColumn(
        modifier = modifier.background(Color.White)
    ) {
        itemsIndexed(
            items = devices,
            key = { _, device -> device.serialNumber }
        ) { index, device ->
            DeviceListItem(
                index = index,
                device = device,
                onDeviceClick = onDeviceClick,
                onEditClick = onEditClick,
                onLongClick = {
                    selectedDevice.value = device
                    showDialog.value = true
                }
            )
            Divider(
                thickness = 2.dp,
                color = Color.Gray
            )
        }
    }

    if (showDialog.value) {
        selectedDevice.value?.let { device ->
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = {
                    Text(
                        text = stringResource(
                            R.string.removeDeviceDialogTitle,
                            device.serialNumber
                        )
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            onRemoveDevice(device.serialNumber)
                            showDialog.value = false
                        }
                    ) {
                        Text(stringResource(R.string.removeDeviceConfirm))
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog.value = false }
                    ) {
                        Text(stringResource(R.string.removeDeviceCancel))
                    }
                }
            )
        }
    }
}

@Composable
fun DeviceListItem(
    index: Int,
    device: UiDeviceModel,
    onDeviceClick: (Long) -> Unit,
    onLongClick: (Long) -> Unit,
    onEditClick: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onLongClick(device.serialNumber) },
                    onTap = { onDeviceClick(device.serialNumber) }
                )
            }
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = device.iconResource),
            contentDescription = stringResource(id = R.string.detailScreenItemImageDescription),
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray, RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = device.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = stringResource(id = R.string.deviceItemSerialNumber, device.serialNumber),
                fontSize = 12.sp
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.baseline_edit_24),
            contentDescription = stringResource(R.string.mainScreenEditIconDescription),
            tint = Color.Gray,
            modifier = Modifier.clickable {
                onEditClick(device.serialNumber)
            }
        )
        Icon(
            painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
            contentDescription = stringResource(id = R.string.mainScreenDeviceArrowIconDescription),
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