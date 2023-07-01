package com.falconteam.infoking.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.falconteam.infoking.R
import com.falconteam.infoking.ui.theme.primaryColor

@Composable
fun Background(modifier: Modifier = Modifier){
    val opacity = 0.2f
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(primaryColor)
            .graphicsLayer(alpha = opacity)
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.texture),
                contentScale = ContentScale.Crop
            )
    ) {}

}
