package com.ezlotest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}
