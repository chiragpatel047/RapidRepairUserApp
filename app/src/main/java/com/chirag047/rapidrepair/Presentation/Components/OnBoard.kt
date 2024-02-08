package com.chirag047.rapidrepair.Presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnBoard(image: Int, heading: String, content: String) {
    Column {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier.padding(30.dp)
        )
        poppinsBoldCenterText(contentText = heading, 22.sp,
            Modifier
                .fillMaxWidth()
                .padding(15.dp))
        poppinsCenterText(
            contentText = content,
            14.sp,
            Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
        )

    }
}