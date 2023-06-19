package com.falconteam.infoking.ui.navigation.admin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoAccounts
import androidx.compose.material.icons.filled.SettingsApplications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AdminBottomBar(
    val route: String,
    val icon: ImageVector
) {
    object BanUsers : AdminBottomBar(
        route = "BAN_USERS",
        icon = Icons.Default.NoAccounts
    )

    object AdminSettings : AdminBottomBar(
        route = "ITEMS_REPOSITION",
        icon = Icons.Default.SettingsApplications
    )
}