package com.rwRunTrackingApp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.rwRunTrackingApp.model.AppDatabase
import com.rwRunTrackingApp.model.TrackingRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        lifecycleScope.launch { // coroutine on Main
            val query = async(Dispatchers.IO) {
                try {
                    val appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
                    val lastUid = appDatabase.trackingDao().getAll().lastOrNull()?.id ?: 0
                    val newUid = lastUid + 1
                    appDatabase.trackingDao().insertAll(TrackingRecord(newUid, Calendar.getInstance().timeInMillis, 10.0, 20.0))
                    "User ${newUid} is added"
                } catch (error: Exception) {
                    error.localizedMessage
                }
            }
            val addDataResult = query.await()
            printLog("Result: ${addDataResult}")
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Hong Kong and move the camera
        val latitude = 22.3193
        val longitude = 114.1694
        val hongKongLatLong = LatLng(latitude, longitude)

        val zoomLevel = 9.5f
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hongKongLatLong, zoomLevel))
    }

    fun printLog(message: String) {
        Log.d("TAG_RW", message)
    }
}