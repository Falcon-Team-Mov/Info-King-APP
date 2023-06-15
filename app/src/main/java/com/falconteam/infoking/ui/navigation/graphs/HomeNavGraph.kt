package com.falconteam.infoking.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.infoking.ui.screens.BottomBar
import com.falconteam.infoking.ui.screens.ScreenContent

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.BATTLE,
        startDestination = BottomBar.Battle.route
    ) {
        composable(route = BottomBar.Ranking.route) {
            ScreenContent(
                name = BottomBar.Ranking.route,
                onClick = {}
            )
        }

        composable(route = BottomBar.Inventory.route) {
            ScreenContent(
                name = BottomBar.Inventory.route,
                onClick = {}
            )
        }
        
        composable(route = BottomBar.Battle.route) {
            ScreenContent(
                name = BottomBar.Battle.route,
                onClick = {}
            )
        }

        composable(route = BottomBar.Map.route) {
            ScreenContent(
                name = BottomBar.Map.route,
                onClick = {}
            )
        }

        composable(route = BottomBar.Profile.route) {
            ScreenContent(
                name = BottomBar.Profile.route,
                onClick = {}
            )
        }
    }
}