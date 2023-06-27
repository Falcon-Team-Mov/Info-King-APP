package com.falconteam.infoking.ui.navigation.user.graphs

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.falconteam.infoking.ui.navigation.PressBackAgainToExit
import com.falconteam.infoking.ui.navigation.graphs.Graph
import com.falconteam.infoking.ui.navigation.user.UserBottomBar
import com.falconteam.infoking.ui.navigation.user.screens.ScreenContent

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(
        route = Graph.BATTLE,
        startDestination = UserBottomBar.Battle.route,
    ) {
        composable(route = UserBottomBar.Ranking.route) {
            BackHandler(
                enabled = true,
                onBack = { navController.navigate(UserBottomBar.Battle.route) }
            )
            ScreenContent(
                name = UserBottomBar.Ranking.route,
                onClick = {},
                navController = navController
            )
        }

        composable(route = UserBottomBar.Inventory.route) {
            BackHandler(
                enabled = true,
                onBack = { navController.navigate(UserBottomBar.Battle.route) }
            )
            ScreenContent(
                name = UserBottomBar.Inventory.route,
                onClick = {},
                navController = navController
            )
        }

        composable(route = UserBottomBar.Battle.route) {
            PressBackAgainToExit(navController)
            ScreenContent(
                name = UserBottomBar.Battle.route,
                onClick = {},
                navController = navController
            )
        }

        composable(route = UserBottomBar.Map.route) {
            BackHandler(
                enabled = true,
                onBack = { navController.navigate(UserBottomBar.Battle.route) }
            )
            ScreenContent(
                name = UserBottomBar.Map.route,
                onClick = {},
                navController = navController
            )
        }

        composable(route = UserBottomBar.Profile.route) {
            BackHandler(
                enabled = true,
                onBack = { navController.navigate(UserBottomBar.Battle.route) }
            )
            ScreenContent(
                name = UserBottomBar.Profile.route,
                onClick = {},
                navController = navController
            )
        }
    }
}