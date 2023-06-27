package com.falconteam.infoking.ui.navigation.graphs

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.falconteam.infoking.data.models.SignUpFormOne
import com.falconteam.infoking.ui.navigation.user.screens.authentication.LoginContent
import com.falconteam.infoking.ui.navigation.user.screens.authentication.SignUpCharacterScreen
import com.falconteam.infoking.ui.navigation.user.screens.authentication.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route
    ) {
        val userType = "admin"

        // Login
        composable(route = AuthScreen.Login.route) {
            LoginContent(
                onClick = {
                    val isValidUser = true
                    // TODO: function to verify credentials

                    if (isValidUser) {
                        if (userType == "user") {
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

        // SignUp
        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(
                onClick = { username, email, password ->
                    if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
                        val routeWithArgs = "${AuthScreen.CharacterSignUp.route}/${"a"}/${"a"}/${"a"}"
                        Log.d("Pruebas", "authNavGraph: $routeWithArgs")
                        navController.navigate(routeWithArgs)
                    }
                    else{
                        val routeWithArgs = "${AuthScreen.CharacterSignUp.route}/${username}/${email}/${password}"
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
                            navController.popBackStack(AuthScreen.Login.route, false)
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
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object CharacterSignUp : AuthScreen(route = "CHARACTER_SIGN_UP")
    object ForgotPass : AuthScreen(route = "FORGOT_PASS")
}