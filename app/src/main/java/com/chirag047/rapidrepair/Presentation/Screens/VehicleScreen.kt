package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.SingleVehicle
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.R

@Composable
fun VehicleScreen(navController: NavController) {

    Box(Modifier.fillMaxSize()) {

        var scroll = rememberScrollState()

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(scroll)
        ) {
            poppinsBoldCenterText(
                contentText = "My vehicle",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            SingleVehicle(R.drawable.car_icon, "Maruti Ertiga", "Car | Toyata | Innova | Petrol")
            SingleVehicle(R.drawable.car_icon, "Nexon", "Car | TATA | EV | Battery")
            SingleVehicle(R.drawable.motorcycle_icon, "MT15", "Bike | Yamaha | Sports | Petrol")

        }

        ExtendedFloatingActionButton(
            text = {
                Text(
                    text = "New vehicle",
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            },
            onClick = {
                navController.navigate("AddNewVehicle")
            },
            icon = { Icon(Icons.Filled.Add, "") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp, 20.dp)
        )
    }
}