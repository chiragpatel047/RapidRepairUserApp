package com.chirag047.rapidrepair.Repository

import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.chirag047.rapidrepair.Api.NotificationApi
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.CenterModel
import com.chirag047.rapidrepair.Model.Coordinates
import com.chirag047.rapidrepair.Model.FirebaseNotificationModel
import com.chirag047.rapidrepair.Model.NotificationModel
import com.chirag047.rapidrepair.Model.OrderModel
import com.chirag047.rapidrepair.Model.PushNotification
import com.chirag047.rapidrepair.Model.UserModel
import com.chirag047.rapidrepair.Model.VehicleModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class DataRepository @Inject constructor(
    val auth: FirebaseAuth, val firestore: FirebaseFirestore, val storage: FirebaseStorage,
    val notificationApi: NotificationApi
) {

    suspend fun getCenters(cityName: String): Flow<ResponseType<List<CenterModel>>> = callbackFlow {

        trySend(ResponseType.Loading())

        firestore.collection("centers").whereEqualTo("centerCity", cityName)
            .addSnapshotListener { value, error ->
                Log.d("getCenterRequestNew", "newRequest")
                trySend(ResponseType.Success(value!!.toObjects(CenterModel::class.java)))
            }
        awaitClose() {
            close()
        }
    }

    suspend fun addNewVehicle(vehicleModel: VehicleModel): Flow<ResponseType<String>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("users").document(auth.currentUser!!.uid).collection("vehicles")
                .document(vehicleModel.vehicleId!!).set(vehicleModel).addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResponseType.Success("Vehicle added successfully"))
                    } else {
                        trySend(ResponseType.Error("Something went wrong"))
                    }
                }
            awaitClose {
                close()
            }
        }

    suspend fun getMyVehicles(): Flow<ResponseType<List<VehicleModel>>> = callbackFlow {

        trySend(ResponseType.Loading())

        firestore.collection("users").document(auth.currentUser!!.uid).collection("vehicles")
            .addSnapshotListener { value, error ->
                trySend(ResponseType.Success(value!!.toObjects(VehicleModel::class.java)))
            }
        awaitClose {
            close()
        }
    }

    suspend fun deleteMyVehicle(vehicleId: String): Flow<ResponseType<String>> = callbackFlow {
        trySend(ResponseType.Loading())

        firestore.collection("users").document(auth.currentUser!!.uid).collection("vehicles")
            .document(vehicleId).delete().addOnCompleteListener {
                if (it.isSuccessful) {
                    trySend(ResponseType.Success("Vehicle deleted"))
                } else {
                    trySend(ResponseType.Error("Something went wrong"))
                }
            }
        awaitClose {
            close()
        }
    }


    suspend fun updateUserCity(city: String): Flow<ResponseType<String>> = callbackFlow {
        trySend(ResponseType.Loading())

        firestore.collection("users").document(auth.currentUser!!.uid).update("city", city)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    trySend(ResponseType.Success("Added"))
                }
            }
        awaitClose {
            close()
        }
    }

    suspend fun updateUserProfilePictureAndPhone(
        userImage: String, userName: String, phoneNo: String, sharedPreferences: SharedPreferences
    ): Flow<ResponseType<String>> = callbackFlow {
        trySend(ResponseType.Loading())

        if (!userImage.equals("")) {

            val ref = storage.reference.child("Users").child("userProfilePhotos")
                .child(System.currentTimeMillis().toString())

            ref.putFile(Uri.parse(userImage)).addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    sharedPreferences.edit().putString("profileImage", it.toString()).apply()

                    firestore.collection("users").document(auth.currentUser!!.uid)
                        .update("userImage", it, "userName", userName, "phoneNo", phoneNo)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                trySend(ResponseType.Success("Updated successfully"))
                                Log.d("profileUpdatedLogSuccess", "updated")
                            }
                        }
                }
            }
        } else {

            firestore.collection("users").document(auth.currentUser!!.uid)
                .update("userName", userName, "phoneNo", phoneNo).addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResponseType.Success("Updated successfully"))
                        Log.d("profileUpdatedLogSuccess", "updated")
                    }
                }
        }
        awaitClose {
            close()
        }
    }

    suspend fun getUserDetails(): Flow<ResponseType<UserModel?>> = callbackFlow {
        trySend(ResponseType.Loading())

        firestore.collection("users").document(auth.currentUser!!.uid)
            .addSnapshotListener { value, error ->
                trySend(ResponseType.Success(value!!.toObject(UserModel::class.java)))
            }
        awaitClose {
            close()
        }
    }

    suspend fun submitOrder(orderModel: OrderModel): Flow<ResponseType<String>> = callbackFlow {

        trySend(ResponseType.Loading())

        firestore.collection("orders")
            .document(orderModel.orderId)
            .set(orderModel)
            .await()

        val notify = withContext(Dispatchers.IO) {

            val notification = PushNotification(
                FirebaseNotificationModel(
                    "New order request",
                    "You have a new order request from " + orderModel.vehicleOwner
                ), "/topics/" + orderModel.corporateId
            )

            try {
                val respose = notificationApi.postNotification(notification)
                trySend(ResponseType.Success("Submitted successfully"))

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        awaitClose {
            close()
        }
    }


    suspend fun getMyRequests(
        userId: String
    ): Flow<ResponseType<List<OrderModel>>> = callbackFlow {

        trySend(ResponseType.Loading())

        firestore.collection("orders").whereEqualTo("userId", userId)
            .addSnapshotListener { value, error ->
                trySend(ResponseType.Success(value!!.toObjects(OrderModel::class.java)))
            }

        awaitClose {
            close()
        }
    }

    suspend fun trackLiveLocation(orderId: String): Flow<ResponseType<Coordinates?>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("liveTrack").document(orderId)
                .addSnapshotListener { value, error ->
                    trySend(ResponseType.Success(value!!.toObject(Coordinates::class.java))!!)
                }

            awaitClose {
                close()
            }
        }

    suspend fun getMyAllNotifications(): Flow<ResponseType<List<NotificationModel>?>> =
        callbackFlow {

            trySend(ResponseType.Loading())

            firestore.collection("users")
                .document(auth.currentUser!!.uid)
                .collection("notifications")
                .orderBy("notificationId", Query.Direction.DESCENDING)
                .addSnapshotListener { value, error ->
                    trySend(ResponseType.Success(value!!.toObjects(NotificationModel::class.java))!!)
                }

            awaitClose {
                close()
            }
        }

}
