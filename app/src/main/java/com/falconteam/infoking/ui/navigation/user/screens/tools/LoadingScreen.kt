package com.falconteam.infoking.ui.navigation.user.screens.tools

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falconteam.infoking.R
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor


@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(0.8f)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxSize(0.4f),
            strokeWidth = 31.dp,
            color = secondaryBlueColor
        )
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .background(color = primaryColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val infoKingLogo = painterResource(R.drawable.infokinglogo)
        Image(
            painter = infoKingLogo,
            contentDescription = null
        )
        Loading()
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}