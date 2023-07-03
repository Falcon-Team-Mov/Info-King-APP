package com.falconteam.infoking.ui.navigation.user

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.ui.graphics.vector.ImageVector

sealed class UserBottomBar (
    val route: String,
    val icon: ImageVector
) {
    object Ranking : UserBottomBar(
        route = "RANKING",
        icon = Icons.Default.Leaderboard
    )

    object Inventory : UserBottomBar(
        route = "INVENTORY",
        icon = Icons.Filled.Inventory2
    )

    object Battle : UserBottomBar(
        route = "BATTLE",
        icon = Icons.Default.Send
    )

    object Map : UserBottomBar(
        route = "MAP",
        icon = Icons.Filled.Map
    )

    object Profile : UserBottomBar(
        route = "PROFILE",
        icon = Icons.Filled.Person
    )
    object Attack: UserBottomBar(
        route = "ATTACK",
        icon = Icons.Default.Send
    )
}
