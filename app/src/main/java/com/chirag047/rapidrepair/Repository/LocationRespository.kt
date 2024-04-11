package com.chirag047.rapidrepair.Repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.chirag047.rapidrepair.Common.ResponseType
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class LocationRespository @Inject constructor(val auth: FirebaseAuth) {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var locationCallback: LocationCallback? = null
    private var locationRequest: LocationRequest? = null

    private var location: Location? = null

    @SuppressLint("MissingPermission")
    suspend fun getUserCurrentLocation(context: Context): Flow<ResponseType<LatLng?>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

            fusedLocationProviderClient.getLastLocation().addOnSuccessListener {
                trySend(ResponseType.Success(LatLng(it.latitude, it.longitude)))
            }

            awaitClose {
                locationCallback?.let {
                    fusedLocationProviderClient?.removeLocationUpdates(it)
                }
                close()
            }
        }

}