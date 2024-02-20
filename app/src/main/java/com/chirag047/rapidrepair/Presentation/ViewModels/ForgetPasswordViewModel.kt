package com.chirag047.rapidrepair.Presentation.ViewModels

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.ViewModel
import com.chirag047.rapidrepair.Repository.FirebaseAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(val firebaseAuthRepository: FirebaseAuthRepository) :
    ViewModel() {

    fun sendPasswordResetLink(email: String) =
        firebaseAuthRepository.sendEmailPasswordResetLink(email)

}