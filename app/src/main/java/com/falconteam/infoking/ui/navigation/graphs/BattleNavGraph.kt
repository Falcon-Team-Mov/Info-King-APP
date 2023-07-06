package com.falconteam.infoking.ui.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.falconteam.infoking.data.models.npc
import com.falconteam.infoking.ui.navigation.user.UserBottomBar
import com.falconteam.infoking.ui.navigation.user.screens.attack.AttackScreen
import com.falconteam.infoking.ui.navigation.user.screens.fight.FightScreen

sealed class BattleNavGraph(
    val route: String,
) {
    object Attack : BattleNavGraph(
        route = "ATTACK",
    )

    object Battle : BattleNavGraph(
        route = "BATTLE",
    )
}

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    composable(route = BattleNavGraph.Attack.route) {
        AttackScreen(
            onBack = {
                navController.navigate(UserBottomBar.Battle.route)
            },
            onAttack = {
                navController.navigate(UserBottomBar.Battle.route)
            }
        )
    }
    composable(route = "BattleNavGraph.Battle.route/{data}") {
        FightScreen(
            data = it.arguments?.getString("data") as? npc ?: npc(
                "",
                "",
                0,
                0,
                0,
                ""
            ),
            onBack = {}
        )
    }
}