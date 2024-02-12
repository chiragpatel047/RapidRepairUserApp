package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.NavigationItem
import com.chirag047.rapidrepair.R

@Composable
fun MainScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var currentNavScreen = remember { mutableStateOf(NavigationItem.HomeNav.route) }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            when (currentNavScreen.value) {
                NavigationItem.HomeNav.route -> {
                    HomeScreen()
                }

                NavigationItem.VehicleNav.route -> {
                    VehicleScreen(navController)
                }

                NavigationItem.ServiceNav.route -> {
                    //SavedNavScreen()
                }

                NavigationItem.ProfileNav.route -> {
                    //CartNavScreen()
                }

            }
        }
//        val navNavHostController = rememberNavController()
//
//        NavHost(
//            navController = navNavHostController,
//            startDestination = NavigationItem.HomeNav.route
//        ) {
//            composable(route = NavigationItem.HomeNav.route) {
//                currentNavScreen.value = NavigationItem.HomeNav.route
//            }
//            composable(route = NavigationItem.OrderNav.route) {
//                currentNavScreen.value = NavigationItem.OrderNav.route
//            }
//            composable(route = NavigationItem.SaveNav.route) {
//                currentNavScreen.value = NavigationItem.SaveNav.route
//            }
//            composable(route = NavigationItem.CartNav.route) {
//                currentNavScreen.value = NavigationItem.CartNav.route
//            }
//            composable(route = NavigationItem.ProfileNav.route) {
//                currentNavScreen.value = NavigationItem.ProfileNav.route
//            }
//        }

        val list = listOf(
            NavigationItem.HomeNav,
            NavigationItem.VehicleNav,
            NavigationItem.ServiceNav,
            NavigationItem.ProfileNav
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            list.forEach {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        currentNavScreen.value = it.route
                    }
                ) {
                    Icon(
                        painter = painterResource(id = it.icon),
                        tint = if (currentNavScreen.value.equals(it.route)) MaterialTheme.colorScheme.primary else Color.LightGray,
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = it.label,
                        color = if (currentNavScreen.value.equals(it.route)) MaterialTheme.colorScheme.primary else Color.LightGray,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }
            }
        }
    }

}