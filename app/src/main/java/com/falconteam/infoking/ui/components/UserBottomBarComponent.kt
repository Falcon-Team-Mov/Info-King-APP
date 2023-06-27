package com.falconteam.infoking.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.falconteam.infoking.ui.navigation.user.UserBottomBar
import com.falconteam.infoking.ui.theme.InfoKingTheme

@Composable
fun UserBottomBarComponent(navController: NavHostController) {
    InfoKingTheme {
        val screens = listOf(
            UserBottomBar.Ranking,
            UserBottomBar.Inventory,
            UserBottomBar.Battle,
            UserBottomBar.Map,
            UserBottomBar.Profile,
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val bottomBarDestination = screens.any {it.route == currentDestination?.route}
        if (bottomBarDestination) {
            Surface(
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            ) {
                BottomNavigation(
                    backgroundColor = MaterialTheme.colorScheme.tertiary
                ) {
                    screens.forEach {screen ->
                        AddItem(
                            screen = screen,
                            currentDestination = currentDestination,
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: UserBottomBar,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val primaryColor = MaterialTheme.colorScheme.primary

    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    val background = if(selected) primaryColor else Color.Transparent

    BottomNavigationItem(
        icon = {
               Box(
                   modifier = Modifier
                       .clip(RoundedCornerShape(10.dp))
                       .background(background)
               )
               {
                   Row(
                       modifier = Modifier
                           .padding(8.dp),
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.spacedBy(2.dp),
                   ){
                       Icon(
                           modifier = Modifier.size(32.dp),
                           imageVector = screen.icon,
                           contentDescription = "Nav Icon",
                           tint = MaterialTheme.colorScheme.secondary
                       )
                   }
               }

        },

        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,

        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}