package com.rwRunTrackingApp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrackingDao {
    @Query("SELECT * FROM trackingrecord")
    fun getAll(): List<TrackingRecord>
//    SELECT SUM(Bill) FROM Table1 GROUP BY AccountNumber
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(trackingRecord: TrackingRecord)

    @Delete
    fun delete(trackingRecord: TrackingRecord)
}