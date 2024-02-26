package com.chirag047.rapidrepair

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.chirag047.rapidrepair.Presentation.Screens.AddDetails
import com.chirag047.rapidrepair.Presentation.Screens.AddNewVehicle
import com.chirag047.rapidrepair.Presentation.Screens.AllowLocation
import com.chirag047.rapidrepair.Presentation.Screens.ChangePasswordScreen
import com.chirag047.rapidrepair.Presentation.Screens.ChooseLocation
import com.chirag047.rapidrepair.Presentation.Screens.EditProfile
import com.chirag047.rapidrepair.Presentation.Screens.ForgetPassword
import com.chirag047.rapidrepair.Presentation.Screens.HomeScreen
import com.chirag047.rapidrepair.Presentation.Screens.LoginScreen
import com.chirag047.rapidrepair.Presentation.Screens.MainScreen
import com.chirag047.rapidrepair.Presentation.Screens.NotificationScreen
import com.chirag047.rapidrepair.Presentation.Screens.OnBoardingScreen
import com.chirag047.rapidrepair.Presentation.Screens.RequestConfirmation
import com.chirag047.rapidrepair.Presentation.Screens.SelectServiceScreen
import com.chirag047.rapidrepair.Presentation.Screens.SelectVehicle
import com.chirag047.rapidrepair.Presentation.Screens.SelectVehicleForServiceScreen
import com.chirag047.rapidrepair.Presentation.Screens.SignUpScreen
import com.chirag047.rapidrepair.Presentation.Screens.TrackNowScreen
import com.chirag047.rapidrepair.Presentation.Screens.TrackScreen
import com.chirag047.rapidrepair.Presentation.Screens.VehicleScreen
import com.chirag047.rapidrepair.Presentation.Screens.WelcomeScreen
import com.chirag047.rapidrepair.Presentation.ViewModels.SignUpViewModel
import com.chirag047.rapidrepair.ui.theme.RapidRepairTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RapidRepairTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val firebaseAuth = Firebase.auth

                    if (firebaseAuth.currentUser != null) {
                        App("AllowLocation")
                    } else {
                        App("WelcomeScreen")
                    }

                }
            }
        }
    }

    @Composable
    fun App(startScreen: String) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = startScreen) {
            composable(route = "WelcomeScreen") {
                WelcomeScreen(navController)
            }
            composable(route = "OnBoardingScreen") {
                OnBoardingScreen(navController)
            }
            composable(route = "SignUpScreen") {
                SignUpScreen(navController)
            }
            composable(route = "LoginScreen") {
                LoginScreen(navController)
            }
            composable(route = "AllowLocation") {
                AllowLocation(navController)
            }
            composable(route = "ForgetPassword") {
                ForgetPassword(navController)
            }
            composable(route = "SelectVehicle") {
                SelectVehicle(navController)
            }
            composable(route = "MainScreen") {
                MainScreen(navController)
            }
            composable(route = "HomeScreen") {
                HomeScreen(navController)
            }
            composable(route = "VehicleScreen") {
                VehicleScreen(navController)
            }
            composable(route = "TrackScreen") {
                TrackScreen(navController)
            }
            composable(route = "AddNewVehicle") {
                AddNewVehicle(navController)
            }
            composable(route = "SelectServiceScreen") {
                SelectServiceScreen(navController)
            }
            composable(route = "SelectVehicleForServiceScreen") {
                SelectVehicleForServiceScreen(navController)
            }
            composable(route = "ChooseLocation") {
                ChooseLocation(navController)
            }
            composable(route = "AddDetails") {
                AddDetails(navController)
            }
            composable(route = "RequestConfirmation") {
                RequestConfirmation(navController)
            }
            composable(route = "EditProfile") {
                EditProfile(navController)
            }
            composable(route = "NotificationScreen") {
                NotificationScreen(navController)
            }
            composable(route = "ChangePasswordScreen") {
                ChangePasswordScreen(navController)
            }
            composable(route = "TrackNowScreen") {
                TrackNowScreen(navController)
            }
        }
    }
}

