package com.falconteam.infoking.ui.navigation.user.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.falconteam.infoking.ui.navigation.user.screens.ranking.RankingScreen
import com.falconteam.infoking.ui.theme.primaryColor

@Composable
fun ScreenContent(
    name: String,
    onClick: () -> Unit,
    navController: NavController,
    Screen: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor),
        contentAlignment = Alignment.Center
    ) {

        Screen()
    }
}

@Preview
@Composable
fun ScreenContentPreview() {
    ScreenContent(
        name = "Prueba",
        onClick = {},
        navController = NavController(LocalContext.current),
        Screen = {
            RankingScreen(false)
        }
    )
}