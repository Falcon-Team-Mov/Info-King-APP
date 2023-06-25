package com.falconteam.infoking.ui.navigation.admin.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.falconteam.infoking.ui.navigation.PressBackAgainToExit
import com.falconteam.infoking.ui.navigation.admin.AdminBottomBar
import com.falconteam.infoking.ui.navigation.graphs.Graph
import com.falconteam.infoking.ui.navigation.user.screens.ScreenContent

fun NavGraphBuilder.adminHomeNavGraph(navController: NavController) {
    navigation(
        route = Graph.ADMIN_HOME,
        startDestination = AdminBottomBar.BanUsers.route
    ) {
        composable(route = AdminBottomBar.BanUsers.route) {
            PressBackAgainToExit()
            ScreenContent(
                name = AdminBottomBar.BanUsers.route,
                onClick = {},
                navController = navController
            )
        }
        composable(route = AdminBottomBar.AdminSettings.route) {
            ScreenContent(
                name = AdminBottomBar.AdminSettings.route,
                onClick = {},
                navController = navController
            )
        }
    }
}