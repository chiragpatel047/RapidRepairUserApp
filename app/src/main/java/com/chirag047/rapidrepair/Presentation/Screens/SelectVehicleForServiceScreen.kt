package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.BottomButton
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.SelectVehicleFromList
import com.chirag047.rapidrepair.Presentation.Components.Vehicle
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.R

@Composable
fun SelectVehicleForServiceScreen(navController: NavController) {

    Box(Modifier.fillMaxSize()) {

        Column(Modifier.fillMaxSize()) {

            val vehicleList = listOf<Vehicle>(
                Vehicle(
                    R.drawable.car_icon, "Maruti Ertiga", "Car | Toyata | Innova | Petrol"
                ),
                Vehicle(
                    R.drawable.motorcycle_icon, "MT15", "Bike | Yamaha | Sports | Petrol"
                ),
                Vehicle(
                    R.drawable.car_icon, "Nexon", "Car | TATA | EV | Battery"
                )

            )

            ActionBarWIthBack(title = "Choose vehicle")

            SelectVehicleFromList(vehicleList)
        }

        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
        ) {
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
                    .align(Alignment.End)
                    .padding(15.dp, 5.dp)
            )
            Box(
                Modifier
                    .wrapContentSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(0.dp, 15.dp, 0.dp, 40.dp)
                ) {
                    FullWidthButton(
                        label = "Continue",
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        navController.navigate("ChooseLocation")
                    }
                }
            }
        }

    }


}