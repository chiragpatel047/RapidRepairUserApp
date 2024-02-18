package com.chirag047.rapidrepair.Repository

import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.UserModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val firebaseFirestore: FirebaseFirestore
) {
    suspend fun createUser(
        userName: String,
        email: String,
        password: String
    ): ResponseType<FirebaseUser> {

        firebaseAuth.createUserWithEmailAndPassword(email, password).await()

        val userModel = UserModel(firebaseAuth.currentUser!!.uid, userName, email, password, "", "")

        firebaseFirestore.collection("users").document(firebaseAuth.currentUser!!.uid)
            .set(userModel).await()

        return try {
            ResponseType.Success(firebaseAuth.currentUser!!)
        } catch (e: Exception) {
            ResponseType.Error(e.localizedMessage.toString())
        }
    }
}