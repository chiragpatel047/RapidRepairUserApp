package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.SingleCardService
import com.chirag047.rapidrepair.Presentation.Components.SingleCardServiceRadio
import com.chirag047.rapidrepair.Presentation.Components.VehicleType
import com.chirag047.rapidrepair.Presentation.Components.customProgressBar
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldText
import com.chirag047.rapidrepair.R

@Composable
fun SelectServiceScreen(
    navController: NavController,
    corporateId: String,
    corporateName: String,
    corporateAddress: String
) {

    val scroll = rememberScrollState()

    val selectedIndex = remember {
        mutableStateOf(-1)
    }

    val showProgressBar = remember {
        mutableStateOf(false)
    }

    var openMySnackbar = remember { mutableStateOf(false) }
    var snackBarMsg = remember { mutableStateOf("") }

    val vehicleList = listOf<ServiceType>(
        ServiceType("Flat Tire", R.drawable.flattire_service_icon),
        ServiceType("Towing Service", R.drawable.towtruck_icon),
        ServiceType("Engine Heat", R.drawable.engine_service_icon),
        ServiceType("Battery Jump Start", R.drawable.battery_service_icon),
        ServiceType("Key Lock Assistance", R.drawable.keys_service_icon),
        ServiceType("Other Services", R.drawable.services_icon)
    )

    Box(
        Modifier
            .fillMaxWidth()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {

            ActionBarWIthBack(title = "Request a service")

            Spacer(modifier = Modifier.padding(4.dp))
            poppinsBoldText(
                contentText = "How can we assist you ?",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            selectedIndex.value = SingleCardServiceRadio(list = vehicleList)

            Spacer(modifier = Modifier.padding(10.dp))
        }

        Box(
            Modifier
                .align(Alignment.BottomCenter)
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
                    if (selectedIndex.value.equals(-1)) {
                        snackBarMsg.value = "Please select service you need"
                        openMySnackbar.value = true
                        return@FullWidthButton
                    }

                    val serviceType = vehicleList.get(selectedIndex.value).title
                    navController.navigate("SelectVehicleForServiceScreen" + "/$corporateId" + "/$corporateName" + "/$corporateAddress" + "/$serviceType")
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


data class ServiceType(val title: String, val icon: Int)