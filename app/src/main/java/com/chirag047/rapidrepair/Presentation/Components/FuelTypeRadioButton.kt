package com.chirag047.rapidrepair.Presentation.Components

import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chirag047.rapidrepair.R

@Composable
fun FuelTypeRadioButton(fuelList: List<String>): Int {
    var selectedIndex = remember { mutableStateOf(-1) }


    val scroll = rememberScrollState()

    Row(
        Modifier
            .fillMaxWidth()
            .horizontalScroll(scroll)
    ) {

        Spacer(modifier = Modifier.padding(10.dp))
        fuelList.forEachIndexed { index, it ->

            Column(
                Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .border(
                        1.dp,
                        if (index.equals(selectedIndex.value)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(25.dp)
                    )
                    .background(if (index.equals(selectedIndex.value)) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.background)
                    .clickable {
                        selectedIndex.value = index
                    },

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Text(
                    text = it,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = if (index.equals(selectedIndex.value)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(20.dp, 10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
    }
    return selectedIndex.value

}

data class FuelType(
    val fuelName: String,
    val fuelIcon: Int
)