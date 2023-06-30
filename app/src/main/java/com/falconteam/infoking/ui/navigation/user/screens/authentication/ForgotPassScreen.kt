package com.falconteam.infoking.ui.navigation.user.screens.authentication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.Typography
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.theme.white
import com.falconteam.infoking.ui.viewmodels.ForgotPassViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassScreen(
    onCodeSend: () -> Unit, onVerifyCode: () -> Unit, onChangePass: () -> Unit

) {
    InfoKingTheme(isSystemInDarkTheme()) {
        val secondaryColor = secondaryBlueColor
        val errorColor = buttonCancelColor

        var usernameInput by rememberSaveable { mutableStateOf("") }
        var usernameError by rememberSaveable { mutableStateOf(false) }
        var usernameSupportingText by rememberSaveable { mutableStateOf("") }
        var usernameSupportingColor =
            if (usernameError) buttonCancelColor else secondaryColor.copy(alpha = 0.5f)
        val maxLength = 32

        var CodeInput by rememberSaveable { mutableStateOf("") }
        var CodeError by rememberSaveable { mutableStateOf(false) }
        var CodeSupportingText by rememberSaveable { mutableStateOf("") }
        var CodeSupportingColor =
            if (CodeError) buttonCancelColor else secondaryColor.copy(alpha = 0.5f)

        var passwordInput by rememberSaveable { mutableStateOf("") }
        var passwordError by rememberSaveable { mutableStateOf(false) }
        var passwordSupportingText by rememberSaveable { mutableStateOf("") }
        var passwordSupportingColor =
            if (passwordError) buttonCancelColor else secondaryColor.copy(alpha = 0.5f)
        var passwordVisible by rememberSaveable { mutableStateOf(false) }


        var isCodeSend by rememberSaveable { mutableStateOf(false) }
        var isCodeVerified by rememberSaveable { mutableStateOf(false) }
        var isPasswordChanged by rememberSaveable { mutableStateOf(false) }

        val viewModel: ForgotPassViewModel = viewModel()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = primaryColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "RECUPERAR CONTRASEÑA",
                color = secondaryColor,
                modifier = Modifier.fillMaxHeight(0.1f)
            )
            Text(
                text = "Ingresa tu correo para recibir un codigo de verificacion",
                color = white.copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(0.7f)
            )
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
                label = { Text("Correo") },
                placeholder = { Text(text = "e.g. DirtyDan") },
                supportingText = {
                    Text(
                        text = usernameSupportingText,
                        color = usernameSupportingColor,
                        style = Typography.labelSmall,
                        modifier = Modifier.padding(top = 2.dp)
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
                ),
                readOnly = isCodeSend
            )
            if (isCodeSend) {
                TextField(
                    modifier = Modifier
                        .padding(vertical = 32.dp, horizontal = 54.dp)
                        .fillMaxWidth(),
                    value = CodeInput,
                    onValueChange = { validValue ->
                        if (validValue.length <= maxLength) {
                            CodeInput = validValue
                        }
                        CodeError = false
                    },
                    textStyle = Typography.bodySmall,
                    label = { Text("Codigo de verificacion") },
                    placeholder = { Text(text = "e.g. DirtyDan") },
                    supportingText = {
                        Text(
                            text = CodeSupportingText,
                            color = CodeSupportingColor,
                            style = Typography.labelSmall,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    },
                    isError = CodeError,
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
                    ),
                    readOnly = isCodeVerified
                )
            }

            if (isCodeVerified) {
                TextField(
                    modifier = Modifier
                        .padding(vertical = 32.dp, horizontal = 54.dp)
                        .fillMaxWidth(),
                    value = passwordInput,
                    onValueChange = { validValue ->
                        if (validValue.length <= maxLength) {
                            passwordInput = validValue
                        }
                        passwordError = false
                    },
                    textStyle = Typography.bodySmall,
                    label = { Text("Nueva contraseña") },
                    placeholder = { Text(text = "••••••••") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    supportingText = {
                        Text(
                            text = passwordSupportingText,
                            color = passwordSupportingColor,
                            style = Typography.labelSmall,
                            modifier = Modifier.padding(top = 2.dp)
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
                    ),
                    readOnly = isPasswordChanged
                )
            }

            Column {
                Button(
                    onClick = {
                        usernameError = usernameInput.isBlank()
                        if(isCodeSend) CodeError = CodeInput.isBlank() or (CodeInput.length != 8)
                        if(isCodeVerified) passwordError = passwordInput.isBlank() or (passwordInput.length < 8)

                        if (!usernameError) {
                            usernameSupportingText = ""

                            if (!isCodeSend) viewModel.ForgotPassEmail(usernameInput)
                            if (!CodeError && isCodeSend) {
                                CodeSupportingText = ""
                                if (!isCodeVerified) viewModel.ForgotPassCode(CodeInput)
                                if (!passwordError && isCodeVerified) {
                                    passwordSupportingText = ""
                                    if (!isPasswordChanged) viewModel.ForgotPass(
                                        usernameInput, passwordInput
                                    )
                                }
                            }
                        } else {
                            usernameSupportingText = "Ingresa un correo o usuario valido"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(secondaryAquaColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 54.dp)
                ) {
                    Text(
                        text = "Cambiar contraseña",
                        style = Typography.headlineSmall,
                        color = Color.White
                    )
                }
            }
        }
        if (viewModel.msg.size == 1 && !isCodeSend) {
            isCodeSend = viewModel.valid[0] == true
            Toast.makeText(
                LocalContext.current, "Codigo enviado", Toast.LENGTH_SHORT
            ).show()
        }
        if (viewModel.msg.size == 2 && !isCodeVerified) {
            isCodeVerified = viewModel.valid[1] == true
        }
        if (viewModel.msg.size == 3 && !isPasswordChanged) {
            isPasswordChanged = viewModel.valid[2] == true
        }
        if (isCodeSend) {
            usernameSupportingText = "Se ha enviado un codigo de verificacion a tu correo"
            usernameSupportingColor = Color.Green
        }
        if (isCodeVerified) {
            CodeSupportingText = "Codigo verificado"
            CodeSupportingColor = Color.Green
        }
        if (isPasswordChanged) {
            passwordSupportingText = "Contraseña cambiada"
            passwordSupportingColor = Color.Green
            onChangePass()
        }
    }
}


@Preview
@Composable
fun ForgotPassScreenPreview() {
    ForgotPassScreen({ }, { }, { })
}