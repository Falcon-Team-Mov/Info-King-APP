package com.falconteam.infoking.ui.navigation.user.graphs

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.recreate
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.falconteam.infoking.data.models.npc
import com.falconteam.infoking.ui.components.ClearDataBattle
import com.falconteam.infoking.ui.components.PreferencesKeysBattle.ATAQUE_NPC
import com.falconteam.infoking.ui.components.PreferencesKeysBattle.DEFENSA_NPC
import com.falconteam.infoking.ui.components.PreferencesKeysBattle.IMAGEN_NPC
import com.falconteam.infoking.ui.components.PreferencesKeysBattle.NOMBRE_NPC
import com.falconteam.infoking.ui.components.PreferencesKeysBattle.VIDA_NPC
import com.falconteam.infoking.ui.components.PressBackAgainToExit
import com.falconteam.infoking.ui.components.getDataBattle
import com.falconteam.infoking.ui.components.setFullDataBattle
import com.falconteam.infoking.ui.navigation.graphs.AuthScreen
import com.falconteam.infoking.ui.navigation.graphs.Graph
import com.falconteam.infoking.ui.navigation.user.UserBottomBar
import com.falconteam.infoking.ui.navigation.user.screens.ScreenContent
import com.falconteam.infoking.ui.navigation.user.screens.attack.AttackScreen
import com.falconteam.infoking.ui.navigation.user.screens.battle.BattleScreen
import com.falconteam.infoking.ui.navigation.user.screens.fight.FightScreen
import com.falconteam.infoking.ui.navigation.user.screens.inventory.InventoryScreen
import com.falconteam.infoking.ui.navigation.user.screens.map.MapScreen
import com.falconteam.infoking.ui.navigation.user.screens.profile.ProfileScreen
import com.falconteam.infoking.ui.navigation.user.screens.ranking.RankingScreen
import kotlinx.coroutines.runBlocking


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
//            ScreenContent(
//                name = UserBottomBar.Map.route,
//                onClick = {},
//                navController = navController,
//                {
//                    BattleScreen(
//                        onClick = {
//                            navController.navigate(UserBottomBar.Attack.route)
//                        }
//                    )
//                }
//            )
            MapScreen()
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
        composable(route = UserBottomBar.Attack.route) {
            val context = LocalContext.current
            AttackScreen(
                onBack = {
                    navController.navigate(UserBottomBar.Battle.route)
                },
                onAttack = {
                    if (it.nombre.isEmpty() && it.vida == 0 && it.ataque == 0 && it.defensa == 0 && it.imagen.isEmpty()) {
                        navController.navigate(UserBottomBar.Battle.route)
                    } else {
                        setFullDataBattle(context, it)
                        navController.navigate("${UserBottomBar.Fight.route}")
                    }
                }
            )
        }
        composable(route = "${UserBottomBar.Fight.route}") { BackStackEntry ->
            val context = LocalContext.current
            val data = npc(
                nombre = runBlocking {
                    getDataBattle(context, NOMBRE_NPC).toString()
                },
                vida = runBlocking {
                    getDataBattle(context, keyInt = VIDA_NPC, type = 2).toString().toInt()
                },
                ataque = runBlocking {
                    getDataBattle(context, keyInt = ATAQUE_NPC, type = 2).toString()
                        .toInt()
                },
                defensa = runBlocking {
                    getDataBattle(context, keyInt = DEFENSA_NPC, type = 2).toString()
                        .toInt()
                },
                imagen = runBlocking {
                    getDataBattle(context, keyString = IMAGEN_NPC).toString()
                }
            )
            FightScreen(
                data = data,
            )
        }
    }
}