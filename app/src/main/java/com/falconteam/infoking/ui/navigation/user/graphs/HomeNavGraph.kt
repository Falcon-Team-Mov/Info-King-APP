package com.falconteam.infoking.ui.navigation.user.graphs

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.infoking.ui.navigation.graphs.Graph
import com.falconteam.infoking.ui.navigation.user.UserBottomBar
import com.falconteam.infoking.ui.navigation.user.screens.ScreenContent

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.BATTLE,
        startDestination = UserBottomBar.Battle.route,
        modifier = Modifier.background(Color(0xFF031926))
    ) {
        composable(route = UserBottomBar.Ranking.route) {
            ScreenContent(
                name = UserBottomBar.Ranking.route,
                onClick = {}
            )
        }

        composable(route = UserBottomBar.Inventory.route) {
            ScreenContent(
                name = UserBottomBar.Inventory.route,
                onClick = {}
            )
        }
        
        composable(route = UserBottomBar.Battle.route) {
            ScreenContent(
                name = UserBottomBar.Battle.route,
                onClick = {}
            )
        }

        composable(route = UserBottomBar.Map.route) {
            ScreenContent(
                name = UserBottomBar.Map.route,
                onClick = {}
            )
        }

        composable(route = UserBottomBar.Profile.route) {
            ScreenContent(
                name = UserBottomBar.Profile.route,
                onClick = {}
            )
        }
    }
}