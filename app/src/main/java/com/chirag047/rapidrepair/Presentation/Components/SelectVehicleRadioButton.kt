package com.chirag047.rapidrepair.Presentation.Components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chirag047.rapidrepair.R

@Composable
fun SelectVehicleRadioButton(vehicleList: List<VehicleType>): Int {

    var selectedIndex = remember { mutableStateOf(-1) }


    val scroll = rememberScrollState()

    Row(
        Modifier
            .fillMaxWidth()
            .horizontalScroll(scroll)
    ) {

        Spacer(modifier = Modifier.padding(10.dp))
        vehicleList.forEachIndexed { index, it ->

            Column(
                Modifier
                    .height(130.dp)
                    .width(130.dp)
                    .padding(10.dp)
                    .border(
                        1.dp,
                        if (index.equals(selectedIndex.value)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        selectedIndex.value = index
                    },

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = it.vehicleIcon),
                        contentDescription = "",
                        tint = if (index.equals(selectedIndex.value)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = it.vehicleName,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = if (index.equals(selectedIndex.value)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                        fontSize = 12.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
    }

    return selectedIndex.value
}

data class VehicleType(
    val vehicleName: String,
    val vehicleIcon: Int
)