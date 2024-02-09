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
import com.chirag047.rapidrepair.Presentation.Components.FullWidthTransparentButton
import com.chirag047.rapidrepair.Presentation.Components.SubjectImage
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.Components.poppinsCenterText
import com.chirag047.rapidrepair.R

@Composable
fun AllowNotification(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(30.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            SubjectImage(
                image = R.drawable.notification_subject_image,
                Modifier.padding(40.dp, 0.dp)
            )
        }

        poppinsBoldCenterText(
            contentText = "Get Notifications",
            22.sp,
            Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        poppinsCenterText(
            contentText = "Allow push notification to get real-time updates on your order status",
            14.sp,
            Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        Spacer(modifier = Modifier.padding(20.dp))

        FullWidthButton(label = "Turn on Notification", MaterialTheme.colorScheme.primary) {
            navController.navigate("main")
        }
        Spacer(modifier = Modifier.padding(5.dp))

        FullWidthTransparentButton(label = "Now now") {
            navController.navigate("main")
        }
        Spacer(modifier = Modifier.padding(50.dp))
    }
}