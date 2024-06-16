package com.ezlotest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ezlotest.data.network.model.DeviceModel

@Dao
interface DeviceDao {
    @Query("SELECT * FROM devices")
    suspend fun getDevices(): List<DeviceModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevices(devices: List<DeviceModel>)

    @Query("SELECT COUNT(*) FROM devices")
    suspend fun getCount(): Int

    @Query("DELETE FROM devices")
    suspend fun clearAll()

    @Query("SELECT * FROM devices WHERE pkDevice = :deviceId")
    suspend fun getDeviceById(deviceId: Long): DeviceModel?

    @Update
    suspend fun updateDevice(device: DeviceModel)
}
