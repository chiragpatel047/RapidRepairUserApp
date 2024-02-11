package com.chirag047.rapidrepair.Presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chirag047.rapidrepair.R

@Composable
fun SingleGarage() {
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
                .clip(RoundedCornerShape(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

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
                    painterResource(id = R.drawable.repair_single_icon),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }


        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 5.dp, 15.dp, 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "",
                        Modifier
                            .size(30.dp)
                            .padding(0.dp, 8.dp, 0.dp, 8.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "8AM - 5PM",
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(0.dp, 6.dp, 0.dp, 5.dp),
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "",
                        Modifier
                            .size(30.dp)
                            .padding(0.dp, 8.dp, 0.dp, 8.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "4.5",
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(0.dp, 7.dp, 0.dp, 5.dp),
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }

            }

            poppinsBoldText(
                contentText = "Gotham Car Reparation",
                size = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp)
            )

            poppinsText(
                contentText = "House 58, Road 8, Block A Katargaam",
                size = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }

    }
}