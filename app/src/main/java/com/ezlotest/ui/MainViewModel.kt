package com.ezlotest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezlotest.R
import com.ezlotest.domain.DeviceRepository
import com.ezlotest.ui.model.UiDeviceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    init {
        fetchDevices()
    }

    private val _devices = MutableStateFlow<List<UiDeviceModel>>(emptyList())
    val devices: StateFlow<List<UiDeviceModel>> get() = _devices

    fun getIndexedDeviceById(id: Long): Pair<UiDeviceModel?, Int> {
        _devices.value.find { it.serialNumber == id }.also { device ->
            return Pair(device, _devices.value.indexOf(device))
        }
    }

    private fun fetchDevices() {
        viewModelScope.launch {
            deviceRepository.fetchDevices().also { devices ->
                _devices.value = devices.map {device ->
                    UiDeviceModel(
                        title = device.title,
                        serialNumber = device.pkDevice,
                        firmware = device.firmware,
                        macAddress = device.macAddress,
                        iconResource = getPlatformDrawable(device.platform),
                        model = getModelName(device.platform)
                    )
                }
            }
        }
    }

    // todo move values to enum [by kostyan]
    private fun getPlatformDrawable(platform: String): Int {
        return when (platform) {
            "Sercomm G450" -> R.drawable.vera_plus_big
            "Sercomm G550" -> R.drawable.vera_secure_big
            else -> R.drawable.vera_edge_big
        }
    }

    private fun getModelName(platform: String): String {
        return when(platform) {
            "Sercomm G450" -> "Vera Plus"
            "Sercomm G550" -> "Vera Secure"
            else -> "Vera Edge"
        }
    }
}