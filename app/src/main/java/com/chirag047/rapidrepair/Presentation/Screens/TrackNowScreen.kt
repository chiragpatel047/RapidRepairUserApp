package com.chirag047.rapidrepair.Presentation.Screens

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.background
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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.ViewModels.TrackNowScreenViewModel
import com.chirag047.rapidrepair.R
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TrackNowScreen(
    navController: NavController, orderId: String, clientAddress: String,
    clientLatitude: String,
    clientLongitude: String,
    corporateAddress: String,
    context: Context
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.outline)
    ) {

        val trackNowScreenViewModel: TrackNowScreenViewModel = hiltViewModel()


        val markerState =
            remember {
                mutableStateOf(
                    MarkerState(
                        position = LatLng(
                            0.0, 0.0
                        )
                    )
                )
            }

        LaunchedEffect(key1 = Unit) {
            CoroutineScope(Dispatchers.IO).launch {
                trackNowScreenViewModel.trackLiveLocation(orderId)
            }
        }

        val result = trackNowScreenViewModel.liveData.collectAsState()

        Column(Modifier.fillMaxWidth()) {
            ActionBarWIthBack(title = "Track mechanic"){

            }
        }

        when (result.value) {
            is ResponseType.Error -> {

            }

            is ResponseType.Loading -> {

            }

            is ResponseType.Success -> {

                markerState.value.position =
                    LatLng(result.value.data!!.lat, result.value.data!!.long)

                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(markerState.value.position, 12f)
                }

                GoogleMap(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    cameraPositionState = cameraPositionState,

                    ) {
                    val zoomForFirstTime = remember {
                        mutableStateOf(true)
                    }

                    if (zoomForFirstTime.value) {
                        cameraPositionState.position =
                            CameraPosition.fromLatLngZoom(
                                markerState.value.position, 15f
                            )
                        zoomForFirstTime.value = false
                    }

                    Marker(
                        state = markerState.value,
                        title = "Mechanic",
                        snippet = "Current location of your Mechanic",
                        icon = getIconFromBitmapVector(context, R.drawable.technician)
                    )

                    Marker(
                        state = MarkerState(
                            LatLng(
                                clientLatitude.toDouble(),
                                clientLongitude.toDouble()
                            )
                        ),
                        title = "You",
                        snippet = "Your location",
                        icon = getIconFromBitmapVector(context, R.drawable.user_on_map_logo)
                    )

                }
            }
        }

        Column(
            Modifier
                .fillMaxWidth()
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
                    painter = painterResource(id = R.drawable.profile_filled_icon),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = clientAddress,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp, 0.dp)
                )

            }

            smallVerticalLine()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.service_center),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = corporateAddress,
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
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(0.dp, 15.dp, 0.dp, 40.dp)
                ) {
                    FullWidthButton(
                        label = "Okay",
                        color = MaterialTheme.colorScheme.primary
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun smallVerticalLine() {

    Row(
        modifier = Modifier
            .height(40.dp)
    ) {
        Spacer(modifier = Modifier.padding(12.dp, 0.dp))
        Divider(
            modifier = Modifier
                .height(40.dp)
                .width(2.dp), color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
fun getIconFromBitmapVector(context: Context, icon: Int): BitmapDescriptor {
    val drawable =
        ContextCompat.getDrawable(context, icon)

    drawable?.setBounds(0, 0, 100, 100)
    val bm = Bitmap.createBitmap(
        100,
        100,
        Bitmap.Config.ARGB_8888
    )

    val canvas = android.graphics.Canvas(bm)
    drawable?.draw(canvas)

    return BitmapDescriptorFactory.fromBitmap(bm)
}
