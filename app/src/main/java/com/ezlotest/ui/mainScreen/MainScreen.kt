package com.ezlotest.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ezlotest.ui.MainViewModel
import com.ezlotest.ui.common.ProfileHeader

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val devicesState by viewModel.devices.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ProfileHeader(modifier = Modifier.weight(2f))
        DeviceList(modifier = Modifier.weight(3f), devices = devicesState)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}