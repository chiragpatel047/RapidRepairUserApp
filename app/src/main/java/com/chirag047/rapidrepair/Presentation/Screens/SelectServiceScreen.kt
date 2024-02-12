package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.SingleCardService
import com.chirag047.rapidrepair.Presentation.Components.VehicleType
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldText
import com.chirag047.rapidrepair.R

@Composable
fun SelectServiceScreen(navController: NavController) {
    val scroll = rememberScrollState()
    Column(
        Modifier
            .fillMaxWidth()
    ) {

        val vehicleList = listOf<ServiceType>(
            ServiceType("Flat Tire", R.drawable.flattire_service_icon),
            ServiceType("Towing Service", R.drawable.towtruck_icon),
            ServiceType("Engine Heat", R.drawable.engine_service_icon),
            ServiceType("Battery Jump Start", R.drawable.battery_service_icon),
            ServiceType("Key Lock Assistance", R.drawable.keys_service_icon),
            ServiceType("Other Services", R.drawable.services_icon)
        )

        ActionBarWIthBack(title = "Request a service")

        Spacer(modifier = Modifier.padding(4.dp))
        poppinsBoldText(
            contentText = "How can we assist you ?",
            size = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 5.dp, 15.dp, 0.dp)
        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 15.dp,
            modifier = Modifier.padding(5.dp, 15.dp)

        ) {
            items(vehicleList.size) { item ->
                SingleCardService(icon = vehicleList.get(item).icon,
                    title = vehicleList.get(item).title,
                    modifier = Modifier
                        .weight(1f)
                        .padding(7.dp, 0.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable {

                        })
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        FullWidthButton(label = "Confirm Issue", color = MaterialTheme.colorScheme.primary) {

        }

    }
}


data class ServiceType(val title: String, val icon: Int)