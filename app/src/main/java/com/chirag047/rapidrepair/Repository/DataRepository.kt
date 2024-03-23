package com.chirag047.rapidrepair.Repository

import android.util.Log
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.CenterModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class DataRepository @Inject constructor(val firestore: FirebaseFirestore) {

    suspend fun getCenters(cityName: String): Flow<ResponseType<List<CenterModel>>> = callbackFlow {

        trySend(ResponseType.Loading())

        firestore.collection("centers").addSnapshotListener { value, error ->
            Log.d("getCenterRequestNew","newRequest")
            trySend(ResponseType.Success(value!!.toObjects(CenterModel::class.java)))
        }
        awaitClose() {
            close()
        }
    }
}