package com.chirag047.rapidrepair.Presentation.Screens

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.OrderModel
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.customProgressBar
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldText
import com.chirag047.rapidrepair.Presentation.Components.poppinsText
import com.chirag047.rapidrepair.Presentation.ViewModels.RequestConfirmationViewModel
import com.chirag047.rapidrepair.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun RequestConfirmation(
    navController: NavController,
    sharedPreferences: SharedPreferences,
    corporateId: String,
    corporateName: String,
    corporateAddress: String,
    serviceType: String,
    vehicleType: String,
    vehicleCompany: String,
    vehicleModel: String,
    vehicleFuelType: String,
    vehicleLicensePlate: String,
    clientAddress: String,
    clientLatitude: String,
    clientLongitude: String,
    clientAddedText: String
) {

    val scroll = rememberScrollState()
    val scope = rememberCoroutineScope()

    val requestConfirmationViewModel: RequestConfirmationViewModel = hiltViewModel()

    val showProgressBar = remember {
        mutableStateOf(false)
    }

    var openMySnackbar = remember { mutableStateOf(false) }
    var snackBarMsg = remember { mutableStateOf("") }

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(scroll)
        ) {
            ActionBarWIthBack(title = "Request Confirmation")

            var icon = R.drawable.car_icon

            if (vehicleType.equals("Car")) {
                icon = R.drawable.car_icon
            } else if (vehicleType.equals("Motorcycle")) {
                icon = R.drawable.motorcycle_icon
            } else if (vehicleType.equals("Rickshaw")) {
                icon = R.drawable.rickshaw_icon
            } else if (vehicleType.equals("Truck")) {
                icon = R.drawable.truck_icon
            } else if (vehicleType.equals("Bus")) {
                icon = R.drawable.bus_icon
            }

            singleTrack(
                icon,
                vehicleCompany + " " + vehicleModel,
                vehicleLicensePlate
            )
            verticalLine()

            singleTrack(
                R.drawable.location_pin_icon,
                sharedPreferences.getString("userCity", "") + " city",
                clientAddress
            )

            verticalLine()

            singleTrack(
                R.drawable.about,
                "Details",
                clientAddedText
            )

            verticalLine()

            singleTrack(
                icon = R.drawable.service_center,
                title = corporateName,
                desc = corporateAddress
            )
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
                    label = "Submit",
                    color = MaterialTheme.colorScheme.primary
                ) {
                    scope.launch(Dispatchers.Main) {

                        val orderModel = OrderModel(
                            System.currentTimeMillis().toString(),
                            sharedPreferences.getString("userId", "")!!,
                            corporateId,
                            corporateName,
                            corporateAddress,
                            sharedPreferences.getString("userName", "")!!,
                            vehicleType,
                            vehicleCompany,
                            vehicleModel,
                            vehicleFuelType,
                            vehicleLicensePlate,
                            serviceType,
                            clientAddress,
                            clientLatitude,
                            clientLongitude,
                            clientAddedText,
                            "Pending"
                        )

                        requestConfirmationViewModel.submitOrder(orderModel).collect {
                            when (it) {
                                is ResponseType.Error -> {
                                    showProgressBar.value = false
                                    snackBarMsg.value = it.errorMsg.toString()
                                    openMySnackbar.value = true
                                }

                                is ResponseType.Loading -> {
                                    showProgressBar.value = true
                                }

                                is ResponseType.Success -> {
                                    showProgressBar.value = false
                                    navController.navigate("SubmittedScreen")
                                }
                            }
                        }
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

@Composable
fun verticalLine() {

    Row(
        modifier = Modifier
            .height(70.dp)
    ) {
        Spacer(modifier = Modifier.padding(20.dp, 0.dp))
        Divider(
            modifier = Modifier
                .height(70.dp)
                .width(2.dp), color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
fun singleTrack(icon: Int, title: String, desc: String) {
    Row(
        Modifier
            .padding(0.dp)
            .clip(RoundedCornerShape(25.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .padding(15.dp, 0.dp, 0.dp, 0.dp)
                .clip(RoundedCornerShape(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

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

        }

        Column(Modifier.weight(1f)) {

            poppinsBoldText(
                contentText = title,
                size = 14.sp,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
            )

            poppinsText(
                contentText = desc,
                size = 12.sp,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
            )
        }

    }
}

