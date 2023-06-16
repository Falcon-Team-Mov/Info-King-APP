package com.falconteam.infoking.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar (
    val route: String,
    val icon: ImageVector
) {
    object Ranking : BottomBar(
        route = "RANKING",
        icon = Icons.Default.Leaderboard
    )

    object Inventory : BottomBar(
        route = "INVENTORY",
        icon = Icons.Filled.Inventory2
    )

    object Battle : BottomBar(
        route = "BATTLE",
        icon = Icons.Default.Send
    )

    object Map : BottomBar(
        route = "MAP",
        icon = Icons.Filled.Map
    )

    object Profile : BottomBar(
        route = "PROFILE",
        icon = Icons.Filled.Person
    )
}
