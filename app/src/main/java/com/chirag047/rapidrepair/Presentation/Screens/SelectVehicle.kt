package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.FullWidthTransparentButton
import com.chirag047.rapidrepair.Presentation.Components.SelectVehicleRadioButton
import com.chirag047.rapidrepair.Presentation.Components.SubjectImage
import com.chirag047.rapidrepair.Presentation.Components.VehicleType
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.Components.poppinsCenterText
import com.chirag047.rapidrepair.R

@Composable
fun SelectVehicle(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            SubjectImage(
                image = R.drawable.vehicles_subject_image,
                Modifier.padding(40.dp, 0.dp)
            )
        }

        poppinsBoldCenterText(
            contentText = "Choose your vehicle",
            22.sp,
            Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        poppinsCenterText(
            contentText = "Please select your vehicle type. it help us to find your service faster",
            14.sp,
            Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        val vehicleList = listOf<VehicleType>(
            VehicleType("Car", R.drawable.car_icon),
            VehicleType("Motorcycle", R.drawable.motorcycle_icon),
            VehicleType("Rickshaw", R.drawable.rickshaw_icon),
            VehicleType("Truck", R.drawable.truck_icon),
            VehicleType("Bus", R.drawable.bus_icon)
        )

        var selectedIndex = remember {
            mutableStateOf(-1)
        }

        selectedIndex.value = SelectVehicleRadioButton(vehicleList)

        Spacer(modifier = Modifier.padding(15.dp))

        FullWidthButton(
            label = "Done",
            color = if (selectedIndex.value.equals(-1)) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary
        ) {
            if (!selectedIndex.value.equals(-1)) {
                navController.popBackStack()
                navController.popBackStack()
                navController.popBackStack()
                navController.popBackStack()
                navController.popBackStack()
                navController.navigate("MainScreen")
            }
        }

        Spacer(modifier = Modifier.padding(50.dp))
    }

}