package com.falconteam.infoking.ui.navigation.graphs

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.falconteam.infoking.data.models.SignUpFormOne
import com.falconteam.infoking.ui.components.PreferencesKeys
import com.falconteam.infoking.ui.components.getData
import com.falconteam.infoking.ui.components.setLastTime
import com.falconteam.infoking.ui.navigation.user.screens.authentication.AuthScreen
import com.falconteam.infoking.ui.navigation.user.screens.authentication.ForgotPassScreen
import com.falconteam.infoking.ui.navigation.user.screens.authentication.LoginScreen
import com.falconteam.infoking.ui.navigation.user.screens.authentication.SignUpCharacterScreen
import com.falconteam.infoking.ui.navigation.user.screens.authentication.SignUpScreen
import com.falconteam.infoking.ui.navigation.user.screens.tools.LoadingScreen
import com.falconteam.infoking.ui.viewmodels.LoginViewModel
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Auth.route
    ) {

        // Auth
        composable(route = AuthScreen.Auth.route) {
            val viewModel: LoginViewModel = viewModel()
            val context = LocalContext.current
            var id by remember { mutableStateOf<Any?>(null) }

            var token by remember { mutableStateOf<Any?>(null) }
            var role by remember { mutableStateOf<Any?>(null) }
            var activation by remember { mutableStateOf(false) }

            runBlocking {
                id = getData(context = context, keyString = PreferencesKeys.ID) as String
                if (!activation) {
                    token = getData(context = context, keyString = PreferencesKeys.TOKEN)
                    role = getData(context = context, keyString = PreferencesKeys.ROLE)
                }
            }
            if (id != null && id != "") {
                viewModel.getUserData(
                    context = context,
                    id = id as String
                )
            } else viewModel.finished.value = true
            Log.d(
                "Prueba",
                "${activation} $role $token ${viewModel.finished.value}"
            )
            if (token == null || role == null && !viewModel.finished.value) {
                LoadingScreen()

            } else if (!activation) {
                if (token == null || token == "" || role == null || role == "" && viewModel.finished.value) {
                    AuthScreen(onClick = {
                        navController.navigate(AuthScreen.Login.route)
                    }) {
                        navController.navigate(AuthScreen.SignUp.route)
                    }
                } else if (viewModel.finished.value) {
                    setLastTime(context, true)
                    if (role == "PLAYER_ROLE") {
                        Log.d("Prueba", "Entro aca")
                        navController.popBackStack()
                        navController.navigate(Graph.BATTLE)
                        viewModel.finished.value = false
                        viewModel.startcount.value = true
                        activation = true
                    } else if (role == "ADMIN_ROLE") {
                        navController.popBackStack()
                        navController.navigate(Graph.ADMIN_HOME)
                        viewModel.finished.value = false
                        viewModel.startcount.value = true
                        activation = true
                    }
                }
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
                        navController.navigate(routeWithArgs)
                    } else {
                        val routeWithArgs =
                            "${AuthScreen.CharacterSignUp.route}/${username}/${email}/${password}"
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