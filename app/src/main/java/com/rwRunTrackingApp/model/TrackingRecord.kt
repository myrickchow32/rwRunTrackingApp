package com.rwRunTrackingApp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

@Entity
data class TrackingRecord(
    @PrimaryKey val id: Int,
    @ColumnInfo val timestamp: Long,
    @ColumnInfo val latitude: Double,
    @ColumnInfo val longitude: Double
)
