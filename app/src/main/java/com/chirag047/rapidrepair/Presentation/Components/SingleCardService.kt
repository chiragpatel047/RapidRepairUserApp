package com.chirag047.rapidrepair.Presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chirag047.rapidrepair.Presentation.Screens.ServiceType
import com.chirag047.rapidrepair.R

@Composable
fun SingleCardService(icon: Int, title: String, modifier: Modifier) {
    Column(
        modifier = modifier,
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
                painterResource(id = icon),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .size(80.dp)
                    .padding(20.dp)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun SingleCardServiceRadio(list: List<ServiceType>): Int {

    var selectedIndex = remember { mutableStateOf(-1) }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 15.dp,
        modifier = Modifier.padding(5.dp, 15.dp)

    ) {
        items(list.size) {
            Column(
                modifier = Modifier
                    .padding(7.dp, 0.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(if (it.equals(selectedIndex.value)) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background)
                    .border(
                        if (it.equals(selectedIndex.value)) 1.dp else 0.5.dp,
                        if (it.equals(selectedIndex.value)) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(25.dp)
                    )
                    .clickable {
                        selectedIndex.value = it
                    },
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.padding(10.dp))
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(80.dp))
                        .background(if (it.equals(selectedIndex.value)) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondaryContainer),
                ) {
                    Icon(
                        painterResource(id = list.get(it).icon),
                        contentDescription = "",
                        tint = if (it.equals(selectedIndex.value)) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(20.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = list.get(it).title,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = if (it.equals(selectedIndex.value)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
    return selectedIndex.value
}