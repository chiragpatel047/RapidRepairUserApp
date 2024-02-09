package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidrepair.Presentation.Components.FilledCustomButton
import com.chirag047.rapidrepair.Presentation.Components.OnBoard
import com.chirag047.rapidrepair.Presentation.Components.OutlinedCustomButton
import com.chirag047.rapidrepair.Presentation.Components.poppinsCenterText
import com.chirag047.rapidrepair.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f
        ) {
            3
        }
        Column {

            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = "Skip",
                textAlign = TextAlign.End,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 5.dp)
                    .clickable {
                        navController.navigate("login")
                    }
            )

            Spacer(modifier = Modifier.padding(16.dp))

            HorizontalPager(state = pagerState) {
                when (it) {
                    0 -> {
                        OnBoard(
                            image = R.drawable.repair_subject_image,
                            heading = "Excellence in Every Service",
                            content = " we redefine excellence in every service, ensuring your vehicle's performance reaches new heights."
                        )
                    }

                    1 -> {
                        OnBoard(
                            image = R.drawable.where_subject_image,
                            heading = "Vehicle Service Wherever You Are!",
                            content = "Wherever the road takes you, our service follows suit. Drive worry-free, we bring the expertise to you!"
                        )
                    }

                    2 -> {
                        OnBoard(
                            image = R.drawable.location_subject_image,
                            heading = "Live Track Your Mechanic",
                            content = "Providing real-time updates on your mechanic, ensuring you're always in control of its arrival."
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 0.dp, 30.dp, 50.dp),
            Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val pageText = pagerState.currentPage + 1
            val coroutineScope = rememberCoroutineScope()

            if (!pageText.equals(1)) {
                OutlinedCustomButton(imageIcon = R.drawable.arrowicon, alpha = 1F) {
                    coroutineScope.launch {
                        pagerState.scrollToPage(pagerState.currentPage - 1)
                    }
                }

            } else {
                OutlinedCustomButton(imageIcon = R.drawable.arrowicon, alpha = 0F) {

                }
            }

            poppinsCenterText(
                contentText = pageText.toString() + " / 3",
                14.sp,
                Modifier.padding(10.dp)
            )

            FilledCustomButton(imageIcon = R.drawable.arrowicon) {
                if (pagerState.currentPage.equals(2)) {
                    navController.navigate("SignUpScreen")
                } else {
                    coroutineScope.launch {
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    }
                }
            }
        }
    }
}