package com.chirag047.rapidrepair.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chirag047.rapidrepair.Presentation.Components.FilledCommonCustomButton
import com.chirag047.rapidrepair.Presentation.Components.FilledCustomButton
import com.chirag047.rapidrepair.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Column(Modifier.fillMaxWidth()) {
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

    }
}