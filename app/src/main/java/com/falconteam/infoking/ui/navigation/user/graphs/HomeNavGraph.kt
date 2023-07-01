package com.falconteam.infoking.ui.navigation.user.graphs

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.core.app.ActivityCompat.recreate
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.falconteam.infoking.ui.components.PressBackAgainToExit
import com.falconteam.infoking.ui.navigation.graphs.AuthScreen
import com.falconteam.infoking.ui.navigation.graphs.Graph
import com.falconteam.infoking.ui.navigation.user.UserBottomBar
import com.falconteam.infoking.ui.navigation.user.screens.ScreenContent
import com.falconteam.infoking.ui.navigation.user.screens.attack.AttackScreen
import com.falconteam.infoking.ui.navigation.user.screens.battle.BattleScreen
import com.falconteam.infoking.ui.navigation.user.screens.inventory.InventoryScreen
import com.falconteam.infoking.ui.navigation.user.screens.profile.ProfileScreen
import com.falconteam.infoking.ui.navigation.user.screens.ranking.RankingScreen


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
                navController = navController,
                {
                    RankingScreen(
                        false
                    )
                }
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
                navController = navController,
                { InventoryScreen() }
            )
        }

        composable(route = UserBottomBar.Battle.route) {
            PressBackAgainToExit(navController)
            ScreenContent(
                name = UserBottomBar.Battle.route,
                onClick = {},
                navController = navController,
                {
                    BattleScreen(
                        onClick = {
                            navController.navigate(UserBottomBar.Attack.route)
                        }
                    )
                }
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
                navController = navController,
                {
                    BattleScreen(
                        onClick = {
                            navController.navigate(UserBottomBar.Attack.route)
                        }
                    )
                }
            )
        }

        composable(route = UserBottomBar.Attack.route) {
            AttackScreen()
        }

        composable(route = UserBottomBar.Profile.route) {
            BackHandler(
                enabled = true,
                onBack = { navController.navigate(UserBottomBar.Battle.route) }
            )
            ScreenContent(
                name = UserBottomBar.Profile.route,
                onClick = {},
                navController = navController,
                {
                    ProfileScreen(
                        onLogout = {
                            recreate(navController.context as Activity)
                            navController.popBackStack(Graph.ROOT, false)
                            navController.navigate(AuthScreen.Auth.route)
                        }
                    )
                }
            )
        }
    }
}