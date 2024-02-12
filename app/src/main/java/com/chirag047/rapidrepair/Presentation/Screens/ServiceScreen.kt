package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.GrayFilledSimpleButton
import com.chirag047.rapidrepair.Presentation.Components.SearchBar
import com.chirag047.rapidrepair.Presentation.Components.SingleGarage
import com.chirag047.rapidrepair.R

@Composable
fun ServiceScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {

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
                        text = "Surat City, India",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.down_arrow_icon),
                        contentDescription = "",
                        modifier = Modifier.size(12.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            GrayFilledSimpleButton(imageIcon = R.drawable.notification) {

            }
        }

        SearchBar("Search service center")

        Spacer(modifier = Modifier.padding(15.dp))

        SingleGarage(navController)
        SingleGarage(navController)
        SingleGarage(navController)

    }
}