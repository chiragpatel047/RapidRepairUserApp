package com.chirag047.rapidrepair.Presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SubjectImage(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "",
        modifier = Modifier.padding(40.dp, 40.dp, 40.dp, 0.dp)
    )
}