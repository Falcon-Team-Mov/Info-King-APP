package com.falconteam.infoking.ui.navigation.graphs

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.falconteam.infoking.ui.components.AdminBottomBarComponent
import com.falconteam.infoking.ui.components.UserBottomBarComponent
import com.falconteam.infoking.ui.navigation.admin.graphs.adminHomeNavGraph
import com.falconteam.infoking.ui.navigation.user.graphs.homeNavGraph
import com.falconteam.infoking.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootNavGraph(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            val actualDestination = navController.currentBackStackEntryAsState().value?.destination?.route.toString()
            val currentBackStackEntry = navController.currentBackStackEntry
            val currentGraph = currentBackStackEntry?.destination?.parent?.route.toString()

            if (currentGraph === Graph.ADMIN_HOME) {
                AdminBottomBarComponent(navController = navController)
            }
            else {
                UserBottomBarComponent(navController = navController)
            }
        }
    ) {
        NavHost(
            modifier = Modifier.background(primaryColor),
            navController = navController,
            route = Graph.ROOT,
            startDestination = Graph.AUTH
        ) {
            authNavGraph(navController = navController)
            homeNavGraph(navController = navController)
            adminHomeNavGraph(navController = navController)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val BATTLE = "battle_graph"
    const val ADMIN_HOME = "adminHome_graph"
}