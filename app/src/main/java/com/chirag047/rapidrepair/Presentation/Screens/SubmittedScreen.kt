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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.SubjectImage
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.Components.poppinsCenterText
import com.chirag047.rapidrepair.R

@Composable
fun SubmittedScreen(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth()) {

            Spacer(modifier = Modifier.padding(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                SubjectImage(
                    image = R.drawable.order_done_subject_image,
                    Modifier.padding(40.dp, 0.dp)
                )
            }

            poppinsBoldCenterText(
                contentText = "Your request submitted",
                size = 22.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 10.dp, 30.dp, 10.dp)
            )

            poppinsCenterText(
                contentText = "Now you can track your request and live location of mechanic",
                size = 14.sp,
                modifier = Modifier.padding(20.dp, 5.dp)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            FullWidthButton(label = "Okay", color = MaterialTheme.colorScheme.primary) {

                navController.popBackStack()
                navController.popBackStack()
                navController.popBackStack()
                navController.popBackStack()
                navController.popBackStack()
                navController.popBackStack()
                navController.popBackStack()

                navController.navigate("MainScreen")
            }

            Spacer(modifier = Modifier.padding(40.dp))
        }


    }
}