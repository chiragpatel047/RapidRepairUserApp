package com.chirag047.rapidrepair.Presentation.Components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chirag047.rapidrepair.Model.VehicleModel
import com.chirag047.rapidrepair.R

@Composable
fun SingleVehicle(vehicleModel: VehicleModel) {

    var icon = R.drawable.car_icon

    if (vehicleModel.vehicleType.equals("Car")) {
        icon = R.drawable.car_icon
    } else if (vehicleModel.vehicleType.equals("Motorcycle")) {
        icon = R.drawable.motorcycle_icon
    } else if (vehicleModel.vehicleType.equals("Rickshaw")) {
        icon = R.drawable.rickshaw_icon
    } else if (vehicleModel.vehicleType.equals("Truck")) {
        icon = R.drawable.truck_icon
    } else if (vehicleModel.vehicleType.equals("Bus")) {
        icon = R.drawable.bus_icon
    }

    val showDropMenu = remember {
        mutableStateOf(false)
    }

    Row(
        Modifier
            .padding(15.dp, 7.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable {
                showDropMenu.value = true
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .padding(15.dp, 0.dp, 7.dp, 0.dp)
                .clip(RoundedCornerShape(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.secondary),
            ) {
                Icon(

                    painterResource(id = icon),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }

        Column(Modifier.weight(1f)) {

            poppinsBoldText(
                contentText = vehicleModel.vehicleCompany!! + " " + vehicleModel.vehicleModel!!,
                size = 14.sp,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
            )

            poppinsText(
                contentText = vehicleModel.vehicleColor!! + " | " + vehicleModel.vehicleFuelType + " | " + vehicleModel.vehicleLicensePlate,
                size = 12.sp,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
            )
        }

        DropDownMenu(showDropMenu.value)

        Spacer(modifier = Modifier.padding(5.dp))

    }
}

@Composable
fun SelectVehicleFromList(vehicleList: List<Vehicle>) {

    var selectedIndex = remember { mutableStateOf(-1) }

    val scroll = rememberScrollState()

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scroll)
    ) {
        vehicleList.forEachIndexed { index, vehicle ->
            Row(
                Modifier
                    .padding(15.dp, 7.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable {
                        selectedIndex.value = index
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    Modifier
                        .padding(15.dp, 0.dp, 7.dp, 0.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(5.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(MaterialTheme.colorScheme.secondary),
                    ) {
                        Icon(
                            painterResource(id = vehicle.icon),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onSecondary,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                }

                Column(Modifier.weight(1f)) {

                    poppinsBoldText(
                        contentText = vehicle.name,
                        size = 14.sp,
                        modifier = Modifier
                            .padding(10.dp, 0.dp)
                    )

                    poppinsText(
                        contentText = vehicle.desc,
                        size = 12.sp,
                        modifier = Modifier
                            .padding(10.dp, 0.dp)
                    )
                }

                Icon(
                    painterResource(id = if (index.equals(selectedIndex.value)) R.drawable.check else R.drawable.oval),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )


                Spacer(modifier = Modifier.padding(5.dp))

            }
        }
    }


}

data class Vehicle(val icon: Int, val name: String, val desc: String)
