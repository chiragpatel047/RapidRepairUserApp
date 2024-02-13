package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.ActionBarWIthBack
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldText
import com.chirag047.rapidrepair.Presentation.Components.poppinsText
import com.chirag047.rapidrepair.R
import kotlin.math.roundToInt

@Composable
fun RequestConfirmation(navController: NavController) {

    val scroll = rememberScrollState()

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(scroll)
        ) {
            ActionBarWIthBack(title = "Request Confirmation")

            singleTrack(R.drawable.car_icon, "Toyota Innova", "GJ 05 SC 3006")
            verticalLine()

            singleTrack(
                R.drawable.location_pin_icon,
                "Surat city",
                "Ring Road , Besides civil hospital, Nanpura 395004"
            )
            verticalLine()

            singleTrack(
                R.drawable.about,
                "Details",
                "I'm in need of tire repair service as soon as..."
            )

            verticalLine()

            singleTrack(
                icon = R.drawable.service_center,
                title = "Gotham Car Reparation",
                desc = "House 58, Road 8, Block A Katargaam"
            )
        }

        Box(
            Modifier
                .align(Alignment.BottomCenter)
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
                    label = "Submit",
                    color = MaterialTheme.colorScheme.primary
                ) {
                    // navController.navigate("SelectVehicleForServiceScreen")
                }
            }
        }

    }
}

@Composable
fun verticalLine() {

    Row(
        modifier = Modifier
            .height(70.dp)
    ) {
        Spacer(modifier = Modifier.padding(20.dp, 0.dp))
        Divider(
            modifier = Modifier
                .height(70.dp)
                .width(2.dp), color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
fun singleTrack(icon: Int, title: String, desc: String) {
    Row(
        Modifier
            .padding(0.dp)
            .clip(RoundedCornerShape(25.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .padding(15.dp, 0.dp, 0.dp, 0.dp)
                .clip(RoundedCornerShape(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.secondary),
            ) {
                Icon(
                    painterResource(id = icon),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                )
            }

        }

        Column(Modifier.weight(1f)) {

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
        }

    }
}

