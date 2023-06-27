package com.falconteam.infoking.ui.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.falconteam.infoking.ui.navigation.user.UserBottomBar
import com.falconteam.infoking.ui.navigation.user.screens.authentication.LoginContent
import com.falconteam.infoking.ui.navigation.user.screens.authentication.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route
    ) {
        val userType = "user"

        // Login
        composable(route = AuthScreen.Login.route) {
            LoginContent(
                onClick = {
                    val isValidUser = true
                    // TODO: function to verify credentials

                    if (isValidUser) {
                        if (userType == "user"){
                            navController.popBackStack()
                            navController.navigate(Graph.BATTLE)
                        } else if (userType == "admin") {
                            navController.popBackStack()
                            navController.navigate(Graph.ADMIN_HOME)
                        }
                    } else {
                        // Invalid credentials
                    }
                }
            ) {
                navController.navigate(AuthScreen.SignUp.route)
            }
        }

        // SignUp
        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen()
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object ForgotPass : AuthScreen(route = "FORGOT_PASS")
}