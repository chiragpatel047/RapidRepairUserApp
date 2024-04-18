package com.chirag047.rapidrepair.Presentation.Screens

import android.content.SharedPreferences
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.OrderModel
import com.chirag047.rapidrepair.Presentation.Components.NoDataText
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldText
import com.chirag047.rapidrepair.Presentation.Components.poppinsText
import com.chirag047.rapidrepair.Presentation.ViewModels.TrackScreenViewModel
import com.chirag047.rapidrepair.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TrackScreen(navController: NavController, sharedPreferences: SharedPreferences) {

    val trackScreenViewModel: TrackScreenViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()


    Box(Modifier.fillMaxSize()) {

        val liveOrdersList = remember {
            mutableListOf<OrderModel>()
        }

        val doneOrdersList = remember {
            mutableListOf<OrderModel>()
        }

        var liverOrdersStatus = remember {
            mutableStateOf("Loading...")
        }

        var doneOrdersStatus = remember {
            mutableStateOf("Loading...")
        }


        Column(Modifier.fillMaxWidth()) {
            poppinsBoldCenterText(
                contentText = "Track",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            val scroll = rememberScrollState()

            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(scroll)
            ) {
                Spacer(modifier = Modifier.padding(4.dp))
                poppinsBoldText(
                    contentText = "Service Request list",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 5.dp, 15.dp, 0.dp)
                )

                Spacer(modifier = Modifier.padding(2.dp))

                val userId = sharedPreferences.getString("userId", "")!!

                LaunchedEffect(key1 = Unit) {
                    scope.launch(Dispatchers.Main) {
                        trackScreenViewModel.getMyOrdersRequest(userId, "Live").collect {
                            when (it) {
                                is ResponseType.Error -> {

                                }

                                is ResponseType.Loading -> {

                                }

                                is ResponseType.Success -> {
                                    liveOrdersList.clear()
                                    liveOrdersList.addAll(it.data!!)
                                    liverOrdersStatus.value = "No Live request"
                                }
                            }
                        }
                    }
                }

                NoDataText(liverOrdersStatus.value, liveOrdersList.size.equals(0))
                loadLiveRequests(liveOrdersList, navController)

                Spacer(modifier = Modifier.padding(6.dp))

                poppinsBoldText(
                    contentText = "Service History",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 5.dp, 15.dp, 0.dp)
                )

                Spacer(modifier = Modifier.padding(6.dp))

                LaunchedEffect(key1 = Unit) {
                    scope.launch(Dispatchers.Main) {
                        trackScreenViewModel.getMyOrdersRequest(userId, "Done").collect {
                            when (it) {
                                is ResponseType.Error -> {

                                }

                                is ResponseType.Loading -> {

                                }

                                is ResponseType.Success -> {
                                    doneOrdersList.clear()
                                    doneOrdersList.addAll(it.data!!)
                                    doneOrdersStatus.value = "No history"
                                }
                            }
                        }
                    }
                }

                NoDataText(doneOrdersStatus.value, doneOrdersList.size.equals(0))
                loadDoneRequests(doneOrdersList, navController)

            }

        }
    }
}


@Composable
fun TrackSingle(title: String, desc: String, onclick: () -> Unit) {
    Row(
        Modifier
            .padding(15.dp, 7.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable {

            }
    ) {

        Column(
            Modifier
                .padding(15.dp, 0.dp, 7.dp, 0.dp)
                .clip(RoundedCornerShape(10.dp))

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
                    painterResource(id = R.drawable.live),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                )
            }
            //Spacer(modifier = Modifier.padding(10.dp))
        }

        Column(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.padding(10.dp))

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

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = {
                        onclick.invoke()
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .padding(15.dp, 10.dp)
                ) {
                    Text(
                        text = "Track now",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }
            }
        }
    }
}


@Composable
fun TrackHistorySingle(title: String, desc: String) {
    Row(
        Modifier
            .padding(15.dp, 7.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable {

            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            Modifier
                .padding(15.dp, 0.dp, 7.dp, 0.dp)
                .clip(RoundedCornerShape(10.dp))

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
                    painterResource(id = R.drawable.history),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }

        Column(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.padding(10.dp))

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

            Spacer(modifier = Modifier.padding(10.dp))

        }

    }
}

@Composable
fun loadLiveRequests(list: List<OrderModel>, navController: NavController) {
    list.forEach {
        TrackSingle(
            it.vehicleOwner,
            it.vehicleCompany + " " + it.vehicleModel + " | " + it.vehicleFuelType
        ) {
            navController.navigate("TrackNowScreen" + "/${it.orderId}" + "/${it.clientAddress}" + "/${it.clientLatitude}" + "/${it.clientLongitude}"+ "/${it.corporateAddress}")
        }
    }
}

@Composable
fun loadDoneRequests(list: List<OrderModel>, navController: NavController) {
    list.forEach {

        TrackHistorySingle(
            it.corporateName,
            it.vehicleCompany + " " + it.vehicleModel + " | " + it.vehicleFuelType
        )
    }
}