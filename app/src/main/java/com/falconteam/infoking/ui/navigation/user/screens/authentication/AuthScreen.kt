package com.falconteam.infoking.ui.navigation.user.screens.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falconteam.infoking.R
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.Typography

@Composable
fun AuthScreen(
    onClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    InfoKingTheme(isSystemInDarkTheme()) {
        val infoKingLogo = painterResource(R.drawable.infokinglogo)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = infoKingLogo,
                contentDescription = "Info King Logo"
            )
            Column(
                modifier = Modifier.padding(top = 60.dp)
            ) {
                Button(
                    onClick = { onClick() },
                    modifier = Modifier
                        .padding(horizontal = 80.dp)
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        text = "INICIAR SESIÓN",
                        style = Typography.headlineSmall,
                        color = Color.White
                    )
                }
                Button(
                    onClick = { onSignUpClick() },
                    modifier = Modifier
                        .padding(horizontal = 80.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        text = "REGISTRARSE",
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
fun AuthScreenPreview() {
    InfoKingTheme {
        AuthScreen(onClick = {}) {}
    }
}