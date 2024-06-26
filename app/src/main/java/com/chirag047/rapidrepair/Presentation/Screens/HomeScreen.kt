package com.chirag047.rapidrepair.Presentation.Screens

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.CenterModel
import com.chirag047.rapidrepair.Presentation.Components.FilledCommonCustomButton
import com.chirag047.rapidrepair.Presentation.Components.FilledCustomButton
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.GrayFilledSimpleButton
import com.chirag047.rapidrepair.Presentation.Components.NoDataText
import com.chirag047.rapidrepair.Presentation.Components.SearchBar
import com.chirag047.rapidrepair.Presentation.Components.SearchChatSingle
import com.chirag047.rapidrepair.Presentation.Components.SingleCardService
import com.chirag047.rapidrepair.Presentation.Components.SingleGarage
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldText
import com.chirag047.rapidrepair.Presentation.Components.poppinsText
import com.chirag047.rapidrepair.Presentation.Components.textWithSeeAllText
import com.chirag047.rapidrepair.Presentation.ViewModels.HomeScreenViewModel
import com.chirag047.rapidrepair.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, sharedPreferences: SharedPreferences) {

    val scroll = rememberScrollState()
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()

    val centerList = remember {
        mutableListOf<CenterModel>()
    }

    val centerListStatus = remember {
        mutableStateOf<String>("Loading...")
    }

    LaunchedEffect(key1 = Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            homeScreenViewModel.getCenter(sharedPreferences.getString("userCity", "")!!)
        }
    }

    val state = homeScreenViewModel.centers.collectAsState()

    Box(Modifier.fillMaxSize()) {

        when (state.value) {

            is ResponseType.Success -> {
                centerList.clear()
                centerList.addAll(state.value.data!!)
                centerListStatus.value = "No centers available"
            }

            is ResponseType.Loading -> {

            }

            is ResponseType.Error -> {
                Log.d("FireBaseDataCenterData", state.value.errorMsg.toString())
            }
        }


        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(scroll)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = "Location",
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 12.sp
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Icon(
                            painter = painterResource(id = R.drawable.location_pin_icon),
                            contentDescription = "",
                            modifier = Modifier.size(15.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = sharedPreferences.getString("userCity", "") +
                                    " City, India",
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp
                        )

                    }
                }
                GrayFilledSimpleButton(imageIcon = R.drawable.notification) {
                    navController.navigate("NotificationScreen")
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 20.dp, 15.dp, 0.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .clickable {
                        navController.navigate("AllCenterListScreen")
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "Get services from",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(25.dp, 0.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = "your location",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(25.dp, 0.dp, 0.dp, 0.dp)
                    )

                    Spacer(modifier = Modifier.padding(6.dp))

                    Button(
                        onClick = {
                            navController.navigate("AllCenterListScreen")
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(25.dp, 0.dp)
                    ) {
                        Text(
                            text = "Find service",
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onTertiary,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.home_cover_image),
                    contentDescription = "",
                    modifier = Modifier.size(150.dp)
                )
            }

            Spacer(modifier = Modifier.padding(4.dp))

            poppinsBoldText(
                contentText = "Book a service",
                size = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            Row(
                Modifier
                    .fillMaxWidth()
            ) {

                SingleCardService(icon = R.drawable.services_icon,
                    title = "Vehicle Service",
                    modifier = Modifier
                        .weight(1f)
                        .padding(15.dp, 0.dp, 7.dp, 0.dp)
                        .shadow(
                            5.dp,
                            RoundedCornerShape(25.dp)
                        )
                        .clip(RoundedCornerShape(25.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable {
                            navController.navigate("AllCenterListScreen")
                        })

                SingleCardService(icon = R.drawable.towtruck_icon,
                    title = "RSA Service",
                    modifier = Modifier
                        .weight(1f)
                        .padding(7.dp, 0.dp, 15.dp, 0.dp)
                        .shadow(
                            5.dp,
                            RoundedCornerShape(25.dp)
                        )
                        .clip(RoundedCornerShape(25.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable {
                            navController.navigate("AllCenterListScreen")
                        })

            }

            Spacer(modifier = Modifier.padding(4.dp))
            textWithSeeAllText(title = "Near you") {
                navController.navigate("AllCenterListScreen")
            }

            loadCenters(list = centerList.take(3), navController = navController)
            NoDataText(centerListStatus.value, centerList.size.equals(0))

        }
    }
}

@Composable
fun loadCenters(list: List<CenterModel>, navController: NavController) {

    list.forEachIndexed { index, centerModel ->
        SingleGarage(navController, centerModel)
    }
}



