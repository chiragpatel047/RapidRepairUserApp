package com.chirag047.rapidrepair.Services

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import com.chirag047.rapidrepair.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationService : Service()  {

    companion object {
        const val CHANNEL_ID = "LOCATIONID"
        const val NOTIFICATION_ID = 20
    }

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var locationCallback: LocationCallback? = null
    private var locationRequest: LocationRequest? = null

    private var notificationManager: NotificationManager? = null

    private var location: Location? = null


    override fun onCreate() {
        super.onCreate()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest =
            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 500)
                .build()

        locationCallback = object : LocationCallback() {
            override fun onLocationAvailability(p0: LocationAvailability) {
                super.onLocationAvailability(p0)
            }

            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                onNewLocation(locationResult)
            }
        }

        notificationManager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "LocationChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager?.createNotificationChannel(notificationChannel)
        }
    }

    @SuppressLint("MissingPermission")
    fun createLocationRequest() {

        try {
            fusedLocationProviderClient?.requestLocationUpdates(
                locationRequest!!,
                locationCallback!!,
                Looper.getMainLooper()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun removeLocationRequest() {
        locationCallback?.let {
            fusedLocationProviderClient?.removeLocationUpdates(it)
        }

        Log.d("StopServiceLogTag", "ServiceStopped")

        stopForeground(true)
        stopSelf()

    }

    @SuppressLint("ForegroundServiceType")
    private fun onNewLocation(locationResult: LocationResult) {

        location = locationResult.lastLocation
        startForeground(NOTIFICATION_ID, getNotification())
    }

    private fun getNotification(): Notification {



        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
        notification.setContentTitle("Location Tracking")
            .setContentTitle("Latitude : ${location?.latitude} Longitude : ${location?.longitude}")
            .setOngoing(true)
            .setSilent(true)
            .setSmallIcon(R.drawable.location_pin_icon)

        return notification.build()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createLocationRequest()
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        removeLocationRequest()
    }
}