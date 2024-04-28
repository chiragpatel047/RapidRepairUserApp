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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.VehicleModel
import com.chirag047.rapidrepair.Presentation.Components.NoDataText
import com.chirag047.rapidrepair.Presentation.Components.SingleVehicle
import com.chirag047.rapidrepair.Presentation.Components.Vehicle
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.ViewModels.VehicleScreenViewModel
import com.chirag047.rapidrepair.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun VehicleScreen(navController: NavController, parentNavController: NavController) {

    val vehicleScreenViewModel: VehicleScreenViewModel = hiltViewModel()

    val scope = rememberCoroutineScope()

    val vehicleList = remember {
        mutableListOf<VehicleModel>()
    }

    val vehicleListStatus = remember {
        mutableStateOf("Loading...")
    }

    LaunchedEffect(key1 = Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            vehicleScreenViewModel.getMyVehicle()
        }
    }

    val state = vehicleScreenViewModel.vehicleList.collectAsState()

    Box(Modifier.fillMaxSize()) {

        when (state.value) {
            is ResponseType.Error -> {

            }

            is ResponseType.Loading -> {

            }

            is ResponseType.Success -> {
                vehicleList.clear()

                vehicleList.addAll(state.value.data!!)

            }
        }

        val scroll = rememberScrollState()

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

            loadVehicles(vehicleList, vehicleScreenViewModel)
            NoDataText(vehicleListStatus.value, vehicleList.size.equals(0))
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


@Composable
fun loadVehicles(vehicleList: List<VehicleModel>, vehicleScreenViewModel: VehicleScreenViewModel) {

    val scope = rememberCoroutineScope()

    vehicleList.forEachIndexed { index, vehicleModel ->
        SingleVehicle(vehicleModel) {
            scope.launch(Dispatchers.Main) {
                vehicleScreenViewModel.deleteVehicle(vehicleModel.vehicleId!!).collect {
                    when (it) {
                        is ResponseType.Error -> {

                        }

                        is ResponseType.Loading -> {

                        }

                        is ResponseType.Success -> {

                        }
                    }
                }
            }
        }
    }
}