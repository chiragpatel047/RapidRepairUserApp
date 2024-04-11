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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.VehicleModel
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.SelectVehicleFromList
import com.chirag047.rapidrepair.Presentation.Components.Vehicle
import com.chirag047.rapidrepair.Presentation.Components.customProgressBar
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.ViewModels.SelectVehicleForServiceViewModel
import com.chirag047.rapidrepair.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SelectVehicleForServiceScreen(
    navController: NavController, corporateId: String, corporateName: String,
    corporateAddress: String, serviceType: String
) {

    val selectVehicleForServiceViewModel: SelectVehicleForServiceViewModel = hiltViewModel()

    val vehicleList = remember {
        mutableStateOf(mutableListOf(VehicleModel()))
    }

    val selectedCarListIndex = remember {
        mutableStateOf(-1)
    }

    val showProgressBar = remember {
        mutableStateOf(false)
    }

    var openMySnackbar = remember { mutableStateOf(false) }
    var snackBarMsg = remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            selectVehicleForServiceViewModel.getMyVehicle()
        }
    }

    val state = selectVehicleForServiceViewModel.vehicleList.collectAsState()

    Box(Modifier.fillMaxSize()) {

        Column(Modifier.fillMaxSize()) {

            ActionBarWIthBack(title = "Choose vehicle")

            when (state.value) {
                is ResponseType.Error -> {

                }

                is ResponseType.Loading -> {

                }

                is ResponseType.Success -> {
                    val list = mutableListOf<VehicleModel>()
                    list.clear()

                    list.addAll(state.value.data!!)
                    vehicleList.value = list

                    selectedCarListIndex.value = SelectVehicleFromList(vehicleList.value)
                }
            }
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
                        if (selectedCarListIndex.value.equals(-1)) {
                            snackBarMsg.value = "Please select vehicle"
                            openMySnackbar.value = true
                            return@FullWidthButton
                        }

                        val selectedVehicle = vehicleList.value.get(selectedCarListIndex.value)
                        navController.navigate("AddDetails" + "/$corporateId" + "/$corporateName" + "/$corporateAddress" + "/$serviceType" + "/${selectedVehicle.vehicleType}" + "/${selectedVehicle.vehicleCompany}" + "/${selectedVehicle.vehicleModel}" + "/${selectedVehicle.vehicleFuelType}" + "/${selectedVehicle.vehicleLicensePlate}")
                    }
                }
            }
        }

        customProgressBar(show = showProgressBar.value, title = "Wait a moment...")
        SnackbarWithoutScaffold(
            snackBarMsg.value, openMySnackbar.value, { openMySnackbar.value = it }, Modifier.align(
                Alignment.BottomCenter
            )
        )
    }
}