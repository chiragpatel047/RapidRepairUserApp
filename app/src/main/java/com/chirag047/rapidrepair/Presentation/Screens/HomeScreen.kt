package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chirag047.rapidrepair.Presentation.Components.FilledCommonCustomButton
import com.chirag047.rapidrepair.Presentation.Components.FilledCustomButton
import com.chirag047.rapidrepair.Presentation.Components.FullWidthButton
import com.chirag047.rapidrepair.Presentation.Components.SingleGarage
import com.chirag047.rapidrepair.Presentation.Components.poppinsBoldText
import com.chirag047.rapidrepair.Presentation.Components.poppinsText
import com.chirag047.rapidrepair.Presentation.Components.textWithSeeAllText
import com.chirag047.rapidrepair.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val scroll = rememberScrollState()

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scroll)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.hamburger_icon),
                contentDescription = "",
                Modifier
                    .size(30.dp)
                    .padding(3.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.location_pin_icon),
                        contentDescription = "",
                        Modifier
                            .size(30.dp)
                            .padding(8.dp, 8.dp, 0.dp, 8.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Delhi",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 5.dp),
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                    Spacer(modifier = Modifier.padding(2.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.down_arrow_icon),
                        contentDescription = "",
                        Modifier
                            .size(30.dp)
                            .padding(10.dp)
                    )

                }
                Icon(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "",
                    Modifier
                        .size(30.dp)
                        .padding(3.dp)
                )

            }

        }


        Row(
            Modifier
                .padding(15.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(15.dp, 15.dp, 5.dp, 15.dp)
                )

                var searchText by remember { mutableStateOf("") }

                TextField(
                    value = searchText,
                    singleLine = true,
                    onValueChange = { searchText = it },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "Search services",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            modifier = Modifier.padding(0.dp, 1.dp, 0.dp, 0.dp)
                        )
                    },

                    modifier = Modifier
                        .background(Color.Transparent)
                )
            }

            Spacer(modifier = Modifier.padding(2.dp))

            FilledCustomButton(imageIcon = R.drawable.filter) {

            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(15.dp, 20.dp, 15.dp, 0.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Get services from",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(25.dp, 0.dp, 0.dp, 0.dp)
                )
                Text(
                    text = "your location",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(25.dp, 0.dp, 0.dp, 0.dp)
                )

                Spacer(modifier = Modifier.padding(6.dp))

                Button(
                    onClick = {
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(25.dp, 0.dp)
                ) {
                    Text(
                        text = "Find service",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.home_cover_image),
                contentDescription = "",
                modifier = Modifier.size(150.dp)
            )

        }

        Spacer(modifier = Modifier.padding(4.dp))

        poppinsBoldText(
            contentText = "Book a service",
            size = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        Row(
            Modifier
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .padding(15.dp, 0.dp, 7.dp, 0.dp)
                    .shadow(
                        5.dp,
                        RoundedCornerShape(25.dp)
                    )
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable {

                    },
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.padding(10.dp))
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(80.dp))
                        .background(MaterialTheme.colorScheme.secondary),
                ) {
                    Icon(
                        painterResource(id = R.drawable.services_icon),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(20.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Vehicle Service",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.padding(10.dp))
            }
            Column(
                Modifier
                    .weight(1f)
                    .padding(7.dp, 0.dp, 15.dp, 0.dp)
                    .shadow(
                        5.dp,
                        RoundedCornerShape(25.dp)
                    )
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable {

                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(10.dp))
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(80.dp))
                        .background(MaterialTheme.colorScheme.secondary)
                ) {
                    Icon(
                        painterResource(id = R.drawable.towtruck_icon),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(20.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "RSA Service",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.padding(10.dp))
            }

        }

        Spacer(modifier = Modifier.padding(4.dp))
        textWithSeeAllText(title = "Near you")
        SingleGarage()
        SingleGarage()
        SingleGarage()
    }
}