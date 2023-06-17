package com.falconteam.infoking.ui.navigation.graphs

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.infoking.ui.screens.home.BattleScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        modifier = Modifier.background(Color(0xFF031926)),
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
}