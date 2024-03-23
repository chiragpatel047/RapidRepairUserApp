package com.chirag047.rapidrepair.Presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chirag047.rapidrepair.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchPlaceholder: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp, 20.dp, 5.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(50.dp)
            ), verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(4.dp))
        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .padding(10.dp, 10.dp, 0.dp, 10.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            ),
            onValueChange = { text = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = searchPlaceholder,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.outline
                )
            },
            modifier = Modifier
                .background(Color.Transparent)
                .weight(1f)
                .height(50.dp)
        )
    }
}


@Composable
fun SearchChatSingle(
    centerName: String,
    centerAddress: String,
    centerTime: String,
    centerRating: String,
    onclick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onclick.invoke()
            }
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
                        text = centerTime,
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
                        text = centerRating,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(0.dp, 7.dp, 0.dp, 5.dp),
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }

            }

            poppinsBoldText(
                contentText = centerName,
                size = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp)
            )

            poppinsText(
                contentText = centerRating,
                size = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }

        Column(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.outline)
        ) {

        }

    }
}