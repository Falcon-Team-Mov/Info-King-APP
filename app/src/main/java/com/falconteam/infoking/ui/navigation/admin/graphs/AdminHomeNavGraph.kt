package com.falconteam.infoking.ui.navigation.admin.graphs

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.infoking.ui.navigation.graphs.Graph
import com.falconteam.infoking.ui.navigation.admin.AdminBottomBar
import com.falconteam.infoking.ui.navigation.user.screens.ScreenContent

@Composable
fun AdminHomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ADMIN_HOME,
        startDestination = AdminBottomBar.BanUsers.route,
        modifier = Modifier.background(Color(0xFF031926))
    ) {
        composable(route = AdminBottomBar.BanUsers.route) {
            ScreenContent(
                name = AdminBottomBar.BanUsers.route,
                onClick = {}
            )
        }
        composable(route = AdminBottomBar.AdminSettings.route) {
            ScreenContent(
                name = AdminBottomBar.AdminSettings.route,
                onClick = {}
            )
        }
    }
}