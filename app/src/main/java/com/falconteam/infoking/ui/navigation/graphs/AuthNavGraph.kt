package com.falconteam.infoking.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.falconteam.infoking.ui.navigation.user.screens.ScreenContent
import com.falconteam.infoking.ui.navigation.user.screens.authentication.LoginContent

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route
    ) {
        val userType = "user"

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
                },
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                },
                onForgotClick = {
                    navController.navigate(AuthScreen.ForgotPass.route)
                }
            )
        }

        composable(route = AuthScreen.SignUp.route) {
            ScreenContent(name = AuthScreen.SignUp.route) {}
        }

        composable(route = AuthScreen.ForgotPass.route) {
            ScreenContent(name = AuthScreen.ForgotPass.route) {}
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object ForgotPass : AuthScreen(route = "FORGOT_PASS")
}