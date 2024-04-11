package com.chirag047.rapidrepair.Presentation.Screens

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.customProgressBar
import com.chirag047.rapidrepair.Presentation.ViewModels.ChooseLocationViewModel
import com.chirag047.rapidrepair.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ChooseLocation(
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


    val activity = LocalContext.current as Activity

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
            }
        })

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {


        val chooseLocationViewModel: ChooseLocationViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()


        val showProgressBar = remember {
            mutableStateOf(false)
        }

        var openMySnackbar = remember { mutableStateOf(false) }
        var snackBarMsg = remember { mutableStateOf("") }

        Column(Modifier.fillMaxWidth()) {
            ActionBarWIthBack(title = "Your Location")
        }

        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                .shadow(10.dp, RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {

            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(80.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.outline)
            ) {

            }

            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.location_pin_icon),
                    contentDescription = "",
                    modifier = Modifier.size(15.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "Surat City, India",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                Modifier
                    .wrapContentSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Column(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(0.dp, 15.dp, 0.dp, 40.dp)
                ) {
                    FullWidthButton(
                        label = "Confirm location",
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        navController.navigate("AddDetails")
                    }

                    Row(
                        modifier = Modifier
                            .padding(15.dp, 10.dp)
                            .border(
                                1.dp, MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(50.dp)
                            )
                            .clickable {

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
                                        chooseLocationViewModel
                                            .getUserCurrentLocation(context)
                                            .collect {
                                                when (it) {
                                                    is ResponseType.Error -> {

                                                    }

                                                    is ResponseType.Loading -> {
                                                        showProgressBar.value = true
                                                    }

                                                    is ResponseType.Success -> {
                                                        showProgressBar.value =false
                                                        snackBarMsg.value = "lat : "+ it.data!!.latitude
                                                        openMySnackbar.value = true
                                                    }
                                                }
                                            }
                                    }


                                }
                            }
                    ) {
                        Text(
                            text = "Use my current location",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        )
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