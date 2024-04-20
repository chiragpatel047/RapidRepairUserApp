package com.chirag047.rapidrepair.Presentation.Screens

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.CenterModel
import com.chirag047.rapidrepair.Presentation.Components.NoDataText
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.ViewModels.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AllCenterListScreen(navController: NavController, sharedPreferences: SharedPreferences) {
    val scroll = rememberScrollState()
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()

    var centerList = remember {
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

        Column(Modifier.fillMaxWidth()) {

            poppinsBoldCenterText(
                contentText = "Near you",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(scroll)
            ) {

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

                loadCenters(list = centerList, navController = navController)
                NoDataText(centerListStatus.value, centerList.size.equals(0))


            }
        }
    }

}