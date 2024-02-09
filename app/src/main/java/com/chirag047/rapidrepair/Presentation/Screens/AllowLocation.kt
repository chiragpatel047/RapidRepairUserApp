package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.SubjectImage
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.Components.poppinsCenterText
import com.chirag047.rapidrepair.R

@Composable
fun AllowLocation(navController: NavController) {
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
            Modifier.fillMaxWidth().padding(15.dp)
        )

        poppinsCenterText(
            contentText = "We need your location to show available near by services",
            14.sp,
            Modifier.fillMaxWidth().padding(15.dp)
        )

        Spacer(modifier = Modifier.padding(20.dp))

        FullWidthButton(label = "Turn on Notification", MaterialTheme.colorScheme.primary) {
            navController.navigate("AllowNotification")
        }
        FullWidthButton(label = "Now now", Color.Transparent) {
            navController.navigate("AllowNotification")
        }

        Spacer(modifier = Modifier.padding(50.dp))


    }
}