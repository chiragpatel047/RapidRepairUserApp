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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chirag047.rapidrepair.Presentation.Screens.AllowLocation
import com.chirag047.rapidrepair.Presentation.Screens.AllowNotification
import com.chirag047.rapidrepair.Presentation.Screens.ForgetPassword
import com.chirag047.rapidrepair.Presentation.Screens.LoginScreen
import com.chirag047.rapidrepair.Presentation.Screens.MainScreen
import com.chirag047.rapidrepair.Presentation.Screens.OnBoardingScreen
import com.chirag047.rapidrepair.Presentation.Screens.SelectVehicle
import com.chirag047.rapidrepair.Presentation.Screens.SignUpScreen
import com.chirag047.rapidrepair.Presentation.Screens.WelcomeScreen
import com.chirag047.rapidrepair.ui.theme.RapidRepairTheme

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
                    App()
                }
            }
        }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "WelcomeScreen") {
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
            composable(route = "AllowNotification") {
                AllowNotification(navController)
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
        }
    }
}

