package com.chirag047.rapidrepair.Repository

import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.UserModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val firebaseFirestore: FirebaseFirestore
) {
    fun createUser(
        userName: String,
        email: String,
        password: String
    ): Flow<ResponseType<FirebaseUser>> = callbackFlow {

        trySend(ResponseType.Loading())

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val userModel =
                    UserModel(firebaseAuth.currentUser!!.uid, userName, email, password, "", "")
                firebaseFirestore.collection("users").document(firebaseAuth.currentUser!!.uid)
                    .set(userModel)
                trySend(ResponseType.Success(firebaseAuth.currentUser!!))
            } else {
                trySend(ResponseType.Error(it.exception!!.message.toString()))
            }
        }

        awaitClose {
            close()
        }

    }

    fun loginUser(email: String, password: String): Flow<ResponseType<FirebaseUser>> =
        callbackFlow {
            trySend(ResponseType.Loading())
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    trySend(ResponseType.Success(it.result.user!!))
                } else {
                    trySend(ResponseType.Error(it.exception!!.message.toString()))
                }
            }
            awaitClose {
                close()
            }

        }

    fun sendEmailPasswordResetLink(email: String): Flow<ResponseType<String>> = callbackFlow {
        trySend(ResponseType.Loading())
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                trySend(ResponseType.Success("Password reset link is sent to your email."))
            } else {
                trySend(ResponseType.Success(it.exception!!.message.toString()))
            }
        }
        awaitClose {
            close()
        }
    }

}