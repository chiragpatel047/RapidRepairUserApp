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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.R

@Composable
fun TrackNowScreen(navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.outline)
    ) {


        Column(Modifier.fillMaxWidth()) {
            ActionBarWIthBack(title = "Track mechanic")
        }

        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                .shadow(10.dp, RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {

            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(80.dp)
                    .height(10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.outline)
            ) {

            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.motorcycle_icon),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "Ring Road , Besides civil hospital, Nanpura 395004",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp, 0.dp)
                )

            }

            smallVerticalLine()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.service_center),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "House 58, Road 8, Block A Katargaam",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp)
                )

            }

            Spacer(modifier = Modifier.padding(5.dp))

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
                        label = "Cancel Request",
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        //navController.navigate("AddDetails")
                    }
                }
            }

        }

    }
}

@Composable
fun smallVerticalLine() {

    Row(
        modifier = Modifier
            .height(40.dp)
    ) {
        Spacer(modifier = Modifier.padding(12.dp, 0.dp))
        Divider(
            modifier = Modifier
                .height(40.dp)
                .width(2.dp), color = MaterialTheme.colorScheme.outline
        )
    }
}