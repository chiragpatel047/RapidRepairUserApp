package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.R

@Composable
fun ChooseLocation(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth()) {
            ActionBarWIthBack(title = "Your Location")

        }
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
        ) {

            Spacer(modifier = Modifier.padding(2.dp))

            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(80.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.outline)
            ) {

            }

            Spacer(modifier = Modifier.padding(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.location_pin_icon),
                    contentDescription = "",
                    modifier = Modifier.size(15.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "Surat City, India",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(15.dp, 25.dp)
                )

            }
            Box(
                Modifier
                    .wrapContentSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(0.dp, 15.dp, 0.dp, 40.dp)
                ) {
                    FullWidthButton(
                        label = "Continue",
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        navController.navigate("ChooseLocation")
                    }
                }
            }

        }

    }
}