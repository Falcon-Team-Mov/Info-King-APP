package com.falconteam.infoking.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.infoking.ui.screens.home.BattleScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.BATTLE) {
            BattleScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val BATTLE = "battle_graph"
    const val RANKING = "ranking_graph"
    const val INVENTORY = "inventory_graph"
    const val MAP = "map_graph"
    const val PROFILE = "profile_graph"
}