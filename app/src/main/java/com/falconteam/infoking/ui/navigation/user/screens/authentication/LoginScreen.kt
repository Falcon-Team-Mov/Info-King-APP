package com.falconteam.infoking.ui.navigation.user.screens.authentication

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onClick: () -> Unit) {
    InfoKingTheme(darkTheme = true) {
        val secondaryColor = MaterialTheme.colorScheme.secondary
        val errorColor = MaterialTheme.colorScheme.error
        val maxLength = 16

        // Inputs
        var usernameInput by rememberSaveable { mutableStateOf("") }
        var passwordInput by rememberSaveable { mutableStateOf("") }

        // Errors
        var usernameError by rememberSaveable { mutableStateOf(false) }
        var passwordError by rememberSaveable { mutableStateOf(false) }

        // Supporting Texts and Colors
        val usernameSupportingText = if (usernameError) "Error: Obligatorio" else "*Obligatorio"
        val usernameSupportingColor =
            if (usernameError) MaterialTheme.colorScheme.error else secondaryColor.copy(alpha = 0.5f)
        val passwordSupportingText = if (passwordError) "Mínimo 8 caracteres" else "*Obligatorio"
        val passwordSupportingColor =
            if (passwordError) MaterialTheme.colorScheme.error else secondaryColor.copy(alpha = 0.5f)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF031926))
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
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(bottom = 64.dp)
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
                    label = { Text("Usuario") },
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
                        textColor = secondaryColor,
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = secondaryColor,
                        focusedLabelColor = secondaryColor,
                        placeholderColor = secondaryColor.copy(alpha = 0.5f),
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
                    modifier = Modifier
                        .padding(vertical = 32.dp, horizontal = 54.dp)
                        .fillMaxWidth(),
                    value = passwordInput,
                    onValueChange = {
                        passwordInput = it
                        passwordError = passwordInput.length < 8
                    },
                    textStyle = Typography.bodySmall,
                    label = { Text("Contraseña") },
                    placeholder = { Text("••••••••") },
                    supportingText = {
                        Text(
                            text = passwordSupportingText,
                            color = passwordSupportingColor,
                            style = Typography.labelSmall,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    },
                    isError = passwordError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Send
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = if (passwordError) errorColor else secondaryColor,
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = secondaryColor,
                        focusedLabelColor = secondaryColor,
                        placeholderColor = secondaryColor.copy(alpha = 0.5f),
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

            ClickableText(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = secondaryColor,
                    fontStyle = Typography.labelSmall.fontStyle,
                    fontWeight = FontWeight.Bold
                )) {
                    append("¿Olvidaste tu contraseña?")
                }
            }, onClick = {

            })

            Column {
                Button(
                    onClick = {
                        usernameError = usernameInput.isBlank()
                        passwordError = passwordInput.isBlank() or (passwordInput.length < 8)
//                        if (!usernameError && !passwordError) onClick(
//                            usernameInput,
//                            passwordInput
//                        )
                        // TODO: added onClick() for testing purposes. ** DELETE AFTER **
                        onClick()
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 54.dp)
                ) {
                    Text(
                        text = "INICIAR SESIÓN",
                        style = Typography.headlineSmall,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    InfoKingTheme {
        LoginScreen(onClick = {})
    }
}