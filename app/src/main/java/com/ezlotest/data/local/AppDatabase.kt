package com.ezlotest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezlotest.data.network.model.DeviceModel

@Database(entities = [DeviceModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deviceDao(): DeviceDao
}
