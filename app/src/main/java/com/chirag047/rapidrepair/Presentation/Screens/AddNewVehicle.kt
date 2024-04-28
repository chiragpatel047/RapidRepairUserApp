package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.VehicleModel
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FuelTypeRadioButton
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.SelectVehicleRadioButton
import com.chirag047.rapidrepair.Presentation.Components.VehicleType
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldText
import com.chirag047.rapidrepair.Presentation.ViewModels.AddNewVehicleViewModel
import com.chirag047.rapidrepair.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewVehicle(navController: NavController) {

    val addNewVehicleViewModel: AddNewVehicleViewModel = hiltViewModel()

    val scroll = rememberScrollState()

    var openMySnackbar = remember { mutableStateOf(false) }
    var snackBarMsg = remember { mutableStateOf("") }

    Box(Modifier.fillMaxSize()) {

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(scroll)
        ) {
            ActionBarWIthBack(title = "Add new vehicle"){

            }

            Spacer(modifier = Modifier.padding(4.dp))
            poppinsBoldText(
                contentText = "Choose your vehicle",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

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

            Spacer(modifier = Modifier.padding(6.dp))
            poppinsBoldText(
                contentText = "Vehicle Company",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            var companyName by remember { mutableStateOf("") }

            TextField(
                value = companyName,
                singleLine = true,
                onValueChange = { companyName = it },
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "e.g Toyota",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)


            )

            Spacer(modifier = Modifier.padding(6.dp))
            poppinsBoldText(
                contentText = "Vehicle Model",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            var vehicleModel by remember { mutableStateOf("") }

            TextField(
                value = vehicleModel,
                singleLine = true,
                onValueChange = { vehicleModel = it },
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "e.g Innova",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)

            )

            Spacer(modifier = Modifier.padding(6.dp))
            poppinsBoldText(
                contentText = "Colour",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            var vehicleColor by remember { mutableStateOf("") }

            TextField(
                value = vehicleColor,
                singleLine = true,
                onValueChange = { vehicleColor = it },
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "e.g Black",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)

            )

            Spacer(modifier = Modifier.padding(6.dp))
            poppinsBoldText(
                contentText = "Fuel Type",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            val fuelList = listOf<String>("Petrol", "Diesel", "CNG", "Battery", "Hydrogen")

            var fuelSelectedIndex = remember {
                mutableStateOf(-1)
            }

            fuelSelectedIndex.value = FuelTypeRadioButton(fuelList)

            Spacer(modifier = Modifier.padding(6.dp))
            poppinsBoldText(
                contentText = "License plate #",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            var vehiclePlateNumber by remember { mutableStateOf("") }

            TextField(
                value = vehiclePlateNumber,
                singleLine = true,
                onValueChange = { vehiclePlateNumber = it },
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "e.g GJ 05 AB XXXX",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)

            )

            Spacer(modifier = Modifier.padding(6.dp))
            poppinsBoldText(
                contentText = "Are any further details that can better help to identify your vehicle ?",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            var otherDescInfo by remember { mutableStateOf("") }

            TextField(
                value = otherDescInfo,
                onValueChange = { otherDescInfo = it },
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "Write something ....",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)

            )

            Spacer(modifier = Modifier.padding(15.dp))

            val scope = rememberCoroutineScope()
            FullWidthButton(label = "Done", color = MaterialTheme.colorScheme.primary) {

                var vehicleType = ""
                val vehicleCompany = companyName
                val vehicleModelName = vehicleModel
                val vehicleColor = vehicleColor
                var vehicleFuelType = ""
                val vehicleLicensePlate = vehiclePlateNumber
                val vehicleOtherInfo = otherDescInfo

                if (selectedIndex.value.equals(-1)) {
                    snackBarMsg.value = "Please select your vehicle type"
                    openMySnackbar.value = true
                    return@FullWidthButton
                }else{
                    vehicleType = vehicleList.get(selectedIndex.value).vehicleName
                }

                if (fuelSelectedIndex.value.equals(-1)) {
                    snackBarMsg.value = "Please select your fuel type"
                    openMySnackbar.value = true
                    return@FullWidthButton
                }else{
                    vehicleFuelType = fuelList.get(fuelSelectedIndex.value)
                }

                if (vehicleCompany.isEmpty() || vehicleModelName.isEmpty() || vehicleColor.isEmpty() || vehicleLicensePlate.isEmpty()) {
                    snackBarMsg.value = "Please fill all details"
                    openMySnackbar.value = true
                    return@FullWidthButton
                }

                val vehicleModel = VehicleModel(
                    System.currentTimeMillis().toString(),
                    vehicleType,
                    vehicleCompany,
                    vehicleModelName,
                    vehicleColor,
                    vehicleFuelType,
                    vehicleLicensePlate,
                    vehicleOtherInfo
                )

                scope.launch(Dispatchers.IO) {
                    addNewVehicleViewModel.addNewVehicle(vehicleModel).collect{
                        when(it){
                            is ResponseType.Error -> {
                                snackBarMsg.value = it.errorMsg.toString()
                                openMySnackbar.value = true
                            }
                            is ResponseType.Loading -> {

                            }
                            is ResponseType.Success -> {

                                snackBarMsg.value = it.data!!
                                openMySnackbar.value = true
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

        }
        SnackbarWithoutScaffold(
            snackBarMsg.value, openMySnackbar.value, { openMySnackbar.value = it }, Modifier.align(
                Alignment.BottomCenter
            )
        )
    }
}