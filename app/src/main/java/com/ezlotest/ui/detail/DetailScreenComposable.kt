package com.ezlotest.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ezlotest.R
import com.ezlotest.ui.MainViewModel
import com.ezlotest.ui.common.ProfileHeader
import com.ezlotest.ui.model.UiDeviceModel
import com.ezlotest.utils.Constants

@Composable
fun DetailScreenComposable(
    modifier: Modifier = Modifier,
    deviceId: Long,
    editMode: Boolean = false,
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {
    val device = viewModel.getDeviceById(deviceId)

    Column(modifier = modifier.fillMaxSize()) {
        ProfileHeader()
        device?.let {
            DeviceDetails(
                device = it,
                editMode = editMode,
                initialTitle = it.title,
                onApplyChanges = { newTitle ->
                    viewModel.updateDeviceTitle(deviceId, newTitle)
                    navController.popBackStack()
                }
            )
        } ?: run {
            Text(text = stringResource(R.string.deviceNotFound))
        }
    }
}

@Composable
fun DeviceDetails(
    modifier: Modifier = Modifier,
    device: UiDeviceModel,
    editMode: Boolean,
    initialTitle: String,
    onApplyChanges: (String) -> Unit = {}
) {
    val titleState = remember {
        mutableStateOf(
            TextFieldValue(
                text = initialTitle,
                selection = TextRange(initialTitle.length)
            )
        )
    }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .background(Color.White)
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = device.iconResource),
                contentDescription = stringResource(id = R.string.detailScreenItemImageDescription),
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            DeviceTitle(editMode, titleState)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "SN: ${device.serialNumber}",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "MAC Address: ${device.macAddress}",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Firmware: ${device.firmware}",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Model: ${device.model}",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (editMode) {
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    onApplyChanges(titleState.value.text)
                }
            ) {
                Text(
                    text = stringResource(R.string.editApplyChangesButton),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun DeviceTitle(
    editMode: Boolean,
    titleState: MutableState<TextFieldValue>
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    if (editMode) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
            titleState.value = titleState.value.copy(
                selection = TextRange(titleState.value.text.length)
            )
        }
        TextField(
            value = titleState.value,
            onValueChange = {
                titleState.value = it.copy(
                    text = it.text,
                    selection = TextRange(it.text.length)
                )
            },
            modifier = Modifier.focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )
    } else {
        Text(
            text = titleState.value.text,
            fontSize = 24.sp
        )
    }
}

@Preview
@Composable
private fun DetailsComposablePreview() {
    DetailScreenComposable(
        deviceId = 35112,
        navController = rememberNavController()
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
    DeviceDetails(
        device = sampleDevice,
        initialTitle = "${Constants.DEVICE_MOCK_TITLE} 1",
        editMode = false
    )
}