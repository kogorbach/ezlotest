package com.ezlotest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezlotest.data.network.model.DeviceModel
import com.ezlotest.domain.DeviceRepository
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

    private val _devices = MutableStateFlow<List<DeviceModel>>(emptyList())
    val devices: StateFlow<List<DeviceModel>> get() = _devices

    private fun fetchDevices() {
        viewModelScope.launch {
            val data = deviceRepository.fetchDevices()
            _devices.value = data
        }
    }
}