package com.chirag047.rapidrepair.Presentation.Screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldCenterText
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldText
import com.chirag047.rapidrepair.Presentation.Components.poppinsText
import com.chirag047.rapidrepair.R

@Composable
fun TrackScreen(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
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

                TrackSingle("Gotham Car Reparation", "Car | Toyata | Innova | Petrol")

                Spacer(modifier = Modifier.padding(6.dp))

                poppinsBoldText(
                    contentText = "Service History",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 5.dp, 15.dp, 0.dp)
                )

                Spacer(modifier = Modifier.padding(6.dp))
                TrackHistorySingle(
                    "Sodhi Garage LTD",
                    "Car | TATA | EV | Battery",
                    "Mon 9 Feb 2024 | 11.47 AM"
                )
                TrackHistorySingle(
                    "Ramesh Corporation",
                    "Bike | Yamaha | Petrol | Battery",
                    "Wed 15 Jan 2024 | 01.35 PM"
                )
                TrackHistorySingle(
                    "CS Mechanics PVT LTD",
                    "Car | TATA | EV | Battery",
                    "Tue 30 Nov 2023 | 6.50 PM"
                )
                TrackHistorySingle(
                    "Sodhi Garage LTD",
                    "Car | TATA | EV | Battery",
                    "Sun 6 Aug 2023 | 07.30 PM"
                )
            }


        }
    }
}


@Composable
fun TrackSingle(title: String, desc: String) {
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
                    onClick = {},
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
fun TrackHistorySingle(title: String, desc: String, dataAndTime: String) {
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

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = dataAndTime,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))

        }

    }
}
