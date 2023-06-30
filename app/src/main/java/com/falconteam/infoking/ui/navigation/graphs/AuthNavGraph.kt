package com.falconteam.infoking.ui.navigation.graphs

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.falconteam.infoking.data.models.SignUpFormOne
import com.falconteam.infoking.ui.navigation.user.screens.authentication.AuthScreen
import com.falconteam.infoking.ui.navigation.user.screens.authentication.ForgotPassScreen
import com.falconteam.infoking.ui.navigation.user.screens.authentication.LoginScreen
import com.falconteam.infoking.ui.navigation.user.screens.authentication.SignUpCharacterScreen
import com.falconteam.infoking.ui.navigation.user.screens.authentication.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Auth.route
    ) {

        // Auth
        composable(route = AuthScreen.Auth.route) {
            AuthScreen(
                onClick = {
                    navController.navigate(AuthScreen.Login.route)
                }
            ) {
                navController.navigate(AuthScreen.SignUp.route)
            }
        }

        // Login
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                onClick = {
                    if (it.user.role == "PLAYER_ROLE") {
                        navController.popBackStack()
                        navController.navigate(Graph.BATTLE)
                        { popUpTo(AuthScreen.Auth.route) { inclusive = true } }
                    } else if (it.user.role == "ADMIN_ROLE") {
                        navController.popBackStack()
                        navController.navigate(Graph.ADMIN_HOME)
                        { popUpTo(AuthScreen.Auth.route) { inclusive = true } }
                    }
                },
                ForgotPassword = {
                    navController.navigate(AuthScreen.ForgotPass.route)
                }
            )
        }

        // Forgot Password
        composable(route = AuthScreen.ForgotPass.route) {
            ForgotPassScreen(
                onCodeSend = {},
                onChangePass = {
                    navController.popBackStack(AuthScreen.Auth.route, false)
                },
                onVerifyCode = {},

                )
        }

        // SignUp
        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(
                onClick = { username, email, password ->
                    if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        val routeWithArgs =
                            "${AuthScreen.CharacterSignUp.route}/${"a"}/${"a"}/${"a"}"
                        Log.d("Pruebas", "authNavGraph: $routeWithArgs")
                        navController.navigate(routeWithArgs)
                    } else {
                        val routeWithArgs =
                            "${AuthScreen.CharacterSignUp.route}/${username}/${email}/${password}"
                        Log.d("Pruebas", "authNavGraph: $routeWithArgs")
                        navController.navigate(routeWithArgs)
                    }
                },
            )
        }

        // Character SignUp
        composable(route = "${AuthScreen.CharacterSignUp.route}/{username}/{email}/{password}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            val infoRegister = SignUpFormOne(username, email, password, "")

            SignUpCharacterScreen(
                onSignUp = {},
                onBack = { msg ->
                    Log.d("Pruebas", "Log de msg: $msg")
                    var returnValue: String? = null
                    msg?.let {
                        if (it == "Cuenta creada exitosamente, verifica tu correo electronico") {
                            navController.popBackStack(AuthScreen.Auth.route, false)
                        }
                        returnValue = it
                    }
                    returnValue ?: ""
                },
                infoRegister = infoRegister
            )

        }
    }
}

sealed class AuthScreen(val route: String) {
    object Auth : AuthScreen(route = "AUTH")
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object CharacterSignUp : AuthScreen(route = "CHARACTER_SIGN_UP")
    object ForgotPass : AuthScreen(route = "FORGOT_PASS")
}