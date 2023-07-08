package com.falconteam.infoking.ui.navigation.user.screens.authentication

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.falconteam.infoking.data.network.dto.login.LoginRequest
import com.falconteam.infoking.data.network.dto.login.LoginResponse
import com.falconteam.infoking.ui.components.TextResponsiveSize
import com.falconteam.infoking.ui.navigation.user.screens.tools.LoadingScreen
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.Typography
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.theme.white
import com.falconteam.infoking.ui.viewmodels.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onClick: (data: LoginResponse) -> Unit,
    ForgotPassword: () -> Unit,
) {
    InfoKingTheme(darkTheme = true) {

        val context = LocalContext.current

        val secondaryColor = secondaryAquaColor
        val errorColor = buttonCancelColor
        val maxLength = 32

        // Inputs
        var usernameInput by rememberSaveable { mutableStateOf("") }
        var passwordInput by rememberSaveable { mutableStateOf("") }

        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        // Errors
        var usernameError by rememberSaveable { mutableStateOf(false) }
        var passwordError by rememberSaveable { mutableStateOf(false) }


        // Supporting Texts and Colors
        val usernameSupportingText = if (usernameError) "Error: Obligatorio" else "*Obligatorio"
        val usernameSupportingColor =
            if (usernameError) buttonCancelColor else secondaryAquaColor.copy(alpha = 0.5f)
        val passwordSupportingText = if (passwordError) "Mínimo 8 caracteres" else "*Obligatorio"
        val passwordSupportingColor =
            if (passwordError) buttonCancelColor else secondaryAquaColor.copy(alpha = 0.5f)
        val loginViewModel: LoginViewModel = viewModel()
        val errors = loginViewModel.errors.value
        var data = loginViewModel.data[0]
        val isLogin = remember {
            mutableStateOf(false)
        }

        if (!isLogin.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(primaryColor)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "INICIO DE SESIÓN",
                        color = secondaryAquaColor,
                        modifier = Modifier.padding(bottom = 64.dp),
                        fontSize = TextResponsiveSize(size = 24.sp)
                    )
                }

                // Username
                Column(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    TextField(
                        modifier = Modifier
                            .padding(vertical = 32.dp, horizontal = 54.dp)
                            .fillMaxWidth(),
                        value = usernameInput,
                        onValueChange = { validValue ->
                            if (validValue.length <= maxLength) {
                                usernameInput = validValue
                            }
                            usernameError = false
                        },
                        textStyle = Typography.bodySmall,
                        label = {
                            Text(
                                "Usuario",
                                fontSize = TextResponsiveSize(size = 20.sp),
                            )
                        },
                        placeholder = { Text(text = "e.g. DirtyDan") },
                        supportingText = {
                            Text(
                                text = usernameSupportingText,
                                color = usernameSupportingColor,
                                style = Typography.labelSmall,
                                modifier = Modifier.padding(top = 2.dp),
                                fontSize = TextResponsiveSize(size = 12.sp)
                            )
                        },
                        isError = usernameError,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = white.copy(alpha = 0.5f),
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = secondaryColor,
                            focusedLabelColor = secondaryColor,
                            placeholderColor = white.copy(alpha = 0.5f),
                            unfocusedLabelColor = secondaryColor,
                            unfocusedIndicatorColor = secondaryColor,
                            selectionColors = TextSelectionColors(
                                handleColor = MaterialTheme.colorScheme.tertiary,
                                backgroundColor = Color.Transparent
                            )
                        )
                    )

                    // Password
                    TextField(
                        value = passwordInput,
                        onValueChange = {
                            passwordInput = it
                            passwordError = passwordInput.length < 8
                        },
                        label = {
                            Text(
                                text = "Contraseña",
                                fontSize = TextResponsiveSize(size = 20.sp),
                            )
                        },
                        placeholder = { Text(text = "••••••••") },
                        textStyle = Typography.bodyLarge,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        supportingText = {
                            Text(
                                text = passwordSupportingText,
                                color = passwordSupportingColor,
                                style = Typography.labelSmall,
                                modifier = Modifier.padding(top = 2.dp),
                                fontSize = TextResponsiveSize(size = 12.sp)
                            )
                        },
                        isError = passwordError,
                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            val description =
                                if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = image,
                                    description,
                                    tint = if (passwordError) errorColor else secondaryColor
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(vertical = 35.dp, horizontal = 54.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = white.copy(alpha = 0.5f),
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = secondaryColor,
                            focusedLabelColor = secondaryColor,
                            placeholderColor = white.copy(alpha = 0.5f),
                            unfocusedLabelColor = secondaryColor,
                            unfocusedIndicatorColor = secondaryColor,
                            cursorColor = MaterialTheme.colorScheme.tertiary,
                            selectionColors = TextSelectionColors(
                                handleColor = MaterialTheme.colorScheme.tertiary,
                                backgroundColor = Color.Transparent
                            )
                        )
                    )
                }

                ClickableText(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = secondaryColor,
                                fontStyle = Typography.labelSmall.fontStyle,
                                fontWeight = FontWeight.Bold,
                                fontSize = TextResponsiveSize(size = 20.sp)
                            )
                        ) {
                            append("¿Olvidaste tu contraseña?")
                        }
                    }, onClick = { ForgotPassword() }
                )

                Column(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Button(
                        onClick = {
                            usernameError = usernameInput.isBlank()
                            passwordError = passwordInput.isBlank() or (passwordInput.length < 8)
                            if (!usernameError && !passwordError) {
                                loginViewModel.Login(
                                    context,
                                    LoginRequest = LoginRequest(
                                        usernameInput,
                                        passwordInput
                                    )
                                )
                                isLogin.value = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(secondaryAquaColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp, horizontal = 54.dp)
                    ) {
                        Text(
                            text = "INICIAR SESIÓN",
                            style = Typography.headlineSmall,
                            color = Color.White,
                            fontSize = TextResponsiveSize(size = 20.sp)
                        )
                    }
                }
            }
        } else {
            LoadingScreen()
        }
        if (isLogin.value) {
            if (data != null) {
                isLogin.value = false
                onClick(data)
                data = null
            }
        }
        if (errors != "" && isLogin.value) {
            Toast.makeText(LocalContext.current, errors, Toast.LENGTH_SHORT).show()
            loginViewModel.errors.value = ""
            isLogin.value = false
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    InfoKingTheme {
        LoginScreen(
            onClick = { },
            ForgotPassword = { }
        )
    }
}