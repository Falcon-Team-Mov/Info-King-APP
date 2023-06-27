package com.falconteam.infoking.ui.navigation.user.screens.authentication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onClick: (username: String, email: String, password: String) -> Unit
) {
    InfoKingTheme(darkTheme = true) {
        val context = LocalContext.current
        val secondaryColor = MaterialTheme.colorScheme.secondary
        val errorColor = MaterialTheme.colorScheme.error
        val maxLength = 16

        var usernameInput by rememberSaveable { mutableStateOf("") }
        var emailInput by rememberSaveable { mutableStateOf("") }
        var passWordInput by rememberSaveable { mutableStateOf("") }
        var repeatPasswordInput by rememberSaveable { mutableStateOf("") }

        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        var repeatPasswordVisible by rememberSaveable { mutableStateOf(false) }

        var usernameError by rememberSaveable { mutableStateOf(false) }
        var emailError by rememberSaveable { mutableStateOf(false) }
        var passwordError by rememberSaveable { mutableStateOf(false) }
        var repeatPasswordError by rememberSaveable { mutableStateOf(false) }
        var isValidEmail by rememberSaveable { mutableStateOf(true) }
        var isValidPassword by rememberSaveable { mutableStateOf(false) }

        val usernameSupportingText = if (usernameError) "Error: Obligatorio" else "*Obligatorio"
        val emailSupportingText =
            if (!isValidEmail) {
                "Ingrese un correo válido"
            } else if (emailError) {
                "Error: Obligatorio"
            } else {
                "*Obligatorio"
            }

        val passwordSupportingText = if (passwordError) "Mínimo 8 caracteres" else "*Obligatorio"
        val repeatPasswordSupportingText =
            if (repeatPasswordError) "Mínimo 8 caracteres" else "*Obligatorio"

        val usernameSupportingColor =
            if (usernameError) MaterialTheme.colorScheme.error else secondaryColor.copy(alpha = 0.5f)
        val emailSupportingColor =
            if (emailError) MaterialTheme.colorScheme.error else secondaryColor.copy(alpha = 0.5f)
        val passwordSupportingColor =
            if (passwordError) MaterialTheme.colorScheme.error else secondaryColor.copy(alpha = 0.5f)
        val repeatPasswordSupportingColor =
            if (repeatPasswordError) MaterialTheme.colorScheme.error else secondaryColor.copy(alpha = 0.5f)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF031926))
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "REGISTRO",
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                )
            }

            // Username
            Column(
                modifier = Modifier
            ) {
                TextField(
                    value = usernameInput,
                    onValueChange = { validValue ->
                        if (validValue.length <= maxLength) {
                            usernameInput = validValue
                        }
                        usernameError = false
                    },
                    label = { Text(text = "Usuario") },
                    placeholder = { Text(text = "e.g. DirtyDan") },
                    textStyle = Typography.bodySmall,
                    singleLine = true,
                    modifier = Modifier
                        .padding(vertical = 35.dp, horizontal = 54.dp)
                        .fillMaxWidth(),
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

                // Email
                TextField(
                    value = emailInput,
                    onValueChange = {
                        emailInput = it
                        emailError = false
                        if (isValidEmail(emailInput)) isValidEmail = true
                    },
                    label = { Text("Correo") },
                    textStyle = Typography.bodySmall,
                    placeholder = { Text(text = "ejemplo@correo.com") },
                    modifier = Modifier
                        .padding(vertical = 35.dp, horizontal = 54.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    supportingText = {
                        Text(
                            text = emailSupportingText,
                            color = emailSupportingColor,
                            style = Typography.labelSmall,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    },
                    isError = emailError or !isValidEmail,
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = secondaryColor,
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

                // Password
                TextField(
                    value = passWordInput,
                    onValueChange = {
                        passWordInput = it
                        passwordError = passWordInput.length < 8
                    },
                    label = { Text(text = "Contraseña") },
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
                    modifier = Modifier
                        .padding(vertical = 35.dp, horizontal = 54.dp)
                        .fillMaxWidth(),
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

                // Repeat password
                TextField(
                    value = repeatPasswordInput,
                    onValueChange = {
                        repeatPasswordInput = it
                        repeatPasswordError = repeatPasswordInput.length < 8
                    },
                    label = { Text(text = "Repetir contraseña") },
                    placeholder = { Text(text = "••••••••") },
                    textStyle = Typography.bodyLarge,
                    singleLine = true,
                    visualTransformation = if (repeatPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Send
                    ),
                    supportingText = {
                        Text(
                            text = repeatPasswordSupportingText,
                            color = repeatPasswordSupportingColor,
                            style = Typography.labelSmall,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    },
                    isError = repeatPasswordError,
                    trailingIcon = {
                        val image = if (repeatPasswordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val description =
                            if (repeatPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"

                        IconButton(onClick = { repeatPasswordVisible = !repeatPasswordVisible }) {
                            Icon(
                                imageVector = image,
                                description,
                                tint = if (repeatPasswordError) errorColor else secondaryColor
                            )
                        }
                    },
                    modifier = Modifier
                        .padding(vertical = 35.dp, horizontal = 54.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = if (repeatPasswordError) errorColor else secondaryColor,
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
                    ),
                )
            }

            Column() {
                Button(
                    onClick = {
                        usernameError = usernameInput.isBlank()
                        emailError = emailInput.isBlank()
                        passwordError = passWordInput.isBlank() or (passWordInput.length < 8)
                        repeatPasswordError =
                            repeatPasswordInput.isBlank() or (repeatPasswordInput.length < 8)
                        isValidEmail = isValidEmail(emailInput)
                        isValidPassword = passWordInput == repeatPasswordInput
                        if (!isValidPassword) Toast.makeText(
                            context,
                            "La contraseña debe ser la misma",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (!usernameError && !emailError && !passwordError && !repeatPasswordError && isValidEmail && isValidPassword) onClick(
                            usernameInput,
                            emailInput,
                            passWordInput
                        )
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 54.dp)
                ) {
                    Text(
                        text = "SIGUIENTE",
                        style = Typography.headlineSmall,
                        color = Color.White
                    )
                }
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,4})+$")
    return email.matches(emailRegex)
}

@Preview
@Composable
fun SignUpScreenPreview() {
    InfoKingTheme(darkTheme = true) {
        SignUpScreen(onClick = { _, _, _ -> })
    }
}