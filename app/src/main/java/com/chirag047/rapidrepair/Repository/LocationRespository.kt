package com.chirag047.rapidrepair.Repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.chirag047.rapidrepair.Common.ResponseType
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LocationRespository @Inject constructor(val auth: FirebaseAuth) {

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var locationCallback: LocationCallback? = null
    private var locationRequest: LocationRequest? = null

    private var location: Location? = null

    @SuppressLint("MissingPermission")
    fun getUserCurrentLocation(context: Context): Flow<ResponseType<LatLng>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

            locationRequest =
                LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 500)
                    .build()


            fusedLocationProviderClient!!.requestLocationUpdates(
                locationRequest!!,
                object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        val lastLocation = locationResult.lastLocation

                        trySend(
                            ResponseType.Success(
                                LatLng(
                                    lastLocation!!.latitude,
                                    lastLocation!!.longitude
                                )
                            )
                        )

                        fusedLocationProviderClient!!.removeLocationUpdates(this)
                    }

                },
                Looper.getMainLooper()
            )

            awaitClose {
                close()
            }
        }
}