package com.chirag047.rapidrepair.Presentation.Screens

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.customProgressBar
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldText
import com.chirag047.rapidrepair.Presentation.ViewModels.AddDetailsViewModel
import com.chirag047.rapidrepair.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDetails(
    navController: NavController,
    context: Context,
    corporateId: String,
    corporateName: String,
    corporateAddress: String,
    serviceType: String,
    vehicleType: String,
    vehicleCompany: String,
    vehicleModel: String,
    vehicleFuelType: String,
    vehicleLicensePlate: String
) {

    Box(Modifier.fillMaxSize()) {

        val addDetailsViewModel: AddDetailsViewModel = hiltViewModel()

        val scope = rememberCoroutineScope()

        val showProgressBar = remember {
            mutableStateOf(false)
        }

        var openMySnackbar = remember { mutableStateOf(false) }
        var snackBarMsg = remember { mutableStateOf("") }

        val activity = LocalContext.current as Activity

        var descInfo by remember { mutableStateOf("") }


        val locationPermission = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { permissions ->
                if (permissions) {
                    val lm =
                        context.getSystemService(LOCATION_SERVICE) as LocationManager
                    if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        val intent: Intent =
                            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        context.startActivity(intent)
                    }

                    scope.launch(Dispatchers.Main) {
                        addDetailsViewModel
                            .getUserCurrentLocation(context)
                            .collect {
                                when (it) {
                                    is ResponseType.Error -> {

                                    }

                                    is ResponseType.Loading -> {
                                        showProgressBar.value = true
                                    }

                                    is ResponseType.Success -> {
                                        showProgressBar.value = false

                                        val geocoder = Geocoder(context, Locale.getDefault())
                                        val address =
                                            geocoder.getFromLocation(
                                                it.data!!.latitude,
                                                it.data!!.longitude,
                                                1
                                            )

                                        val clientAddress =
                                            address!!.get(0).getAddressLine(0).toString()

                                        val clientLatitude = it.data!!.latitude
                                        val clientLongitude = it.data!!.longitude

                                        var clientAddedText = "Nothing added by client"

                                        if(!descInfo.equals("")){
                                            clientAddedText=descInfo
                                        }

                                        navController.navigate("RequestConfirmation" + "/$corporateId" + "/$corporateName" + "/$corporateAddress" + "/$serviceType" + "/${vehicleType}" + "/${vehicleCompany}" + "/${vehicleModel}" + "/${vehicleFuelType}" + "/${vehicleLicensePlate}" + "/${clientAddress}" + "/${clientLatitude}" + "/${clientLongitude}" + "/${clientAddedText}")

                                    }
                                }
                            }
                    }

                } else {
                    snackBarMsg.value = "Please allow location permission to continue"
                    openMySnackbar.value = true
                }
            })

        Column(Modifier.fillMaxWidth()) {
            ActionBarWIthBack(title = "Add details")

            Spacer(modifier = Modifier.padding(2.dp))

            poppinsBoldText(
                contentText = "Are any further details you'd like to pass on to your service provider ?",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            Spacer(modifier = Modifier.padding(4.dp))


            TextField(
                value = descInfo,
                onValueChange = { descInfo = it },
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "Write something .... (Optional)",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(20.dp, 5.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(25.dp))

            )

            Spacer(modifier = Modifier.padding(6.dp))

            FullWidthButton(label = "Continue", color = MaterialTheme.colorScheme.primary) {

                if (ActivityCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED

                    ||
                    ActivityCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    locationPermission.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )

                } else {
                    val lm =
                        context.getSystemService(LOCATION_SERVICE) as LocationManager
                    if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        val intent: Intent =
                            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        context.startActivity(intent)
                    }

                    scope.launch(Dispatchers.Main) {
                        addDetailsViewModel
                            .getUserCurrentLocation(context)
                            .collect {
                                when (it) {
                                    is ResponseType.Error -> {

                                    }

                                    is ResponseType.Loading -> {
                                        showProgressBar.value = true
                                    }

                                    is ResponseType.Success -> {
                                        showProgressBar.value = false

                                        val geocoder = Geocoder(context, Locale.getDefault())
                                        val address =
                                            geocoder.getFromLocation(
                                                it.data!!.latitude,
                                                it.data!!.longitude,
                                                1
                                            )

                                        val clientAddress =
                                            address!!.get(0).getAddressLine(0).toString()

                                        val clientLatitude = it.data!!.latitude
                                        val clientLongitude = it.data!!.longitude

                                        var clientAddedText = "Nothing added by client"

                                        if(!descInfo.equals("")){
                                            clientAddedText=descInfo
                                        }

                                        navController.navigate("RequestConfirmation" + "/$corporateId" + "/$corporateName" + "/$corporateAddress" + "/$serviceType" + "/${vehicleType}" + "/${vehicleCompany}" + "/${vehicleModel}" + "/${vehicleFuelType}" + "/${vehicleLicensePlate}" + "/${clientAddress}" + "/${clientLatitude}" + "/${clientLongitude}" + "/${clientAddedText}")

                                    }
                                }
                            }
                    }

                }
            }

            Spacer(modifier = Modifier.padding(20.dp))

        }

        customProgressBar(show = showProgressBar.value, title = "Wait a moment...")
        SnackbarWithoutScaffold(
            snackBarMsg.value, openMySnackbar.value, { openMySnackbar.value = it }, Modifier.align(
                Alignment.BottomCenter
            )
        )
    }

}