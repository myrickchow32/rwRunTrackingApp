package com.rwRunTrackingApp.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(TrackingRecord::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackingDao(): TrackingDao
}