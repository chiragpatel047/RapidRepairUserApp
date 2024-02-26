package com.chirag047.rapidrepair.Presentation.Screens

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.FullWidthTransparentButton
import com.chirag047.rapidrepair.Presentation.Components.SubjectImage
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.Components.poppinsCenterText
import com.chirag047.rapidrepair.R

@Composable
fun AllowLocation(navController: NavController) {

    val activity = LocalContext.current as Activity
    val locationPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            permissions.entries.forEach {
                if (it.value) {
                    navController.navigate("SelectVehicle")
                } else {
                    navController.navigate("SelectVehicle")
                }
            }
        })

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(30.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            SubjectImage(
                image = R.drawable.current_location_subject_image,
                Modifier.padding(40.dp, 0.dp)
            )
        }

        poppinsBoldCenterText(
            contentText = "What is Your Location?",
            22.sp,
            Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        poppinsCenterText(
            contentText = "We need your location to show available near by services",
            14.sp,
            Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        Spacer(modifier = Modifier.padding(20.dp))

        FullWidthButton(label = "Enable Location", MaterialTheme.colorScheme.primary) {
            if (ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED

                ||
                ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                locationPermission.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }


        Spacer(modifier = Modifier.padding(5.dp))

        FullWidthTransparentButton(label = "Now now") {
            navController.navigate("SelectVehicle")
        }

        Spacer(modifier = Modifier.padding(50.dp))
    }
}