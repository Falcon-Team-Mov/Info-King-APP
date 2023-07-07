package com.falconteam.infoking.ui.navigation.user.screens.battle

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.falconteam.infoking.ui.components.Background
import com.falconteam.infoking.ui.components.ElementResponsiveSize
import com.falconteam.infoking.ui.components.PopUpOneButtonDescription
import com.falconteam.infoking.ui.components.PreferencesKeys
import com.falconteam.infoking.ui.components.TextResponsiveSize
import com.falconteam.infoking.ui.components.getData
import com.falconteam.infoking.ui.components.setData
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.jostBold
import com.falconteam.infoking.ui.theme.jostRegular
import com.falconteam.infoking.ui.theme.jostSemiBold
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.theme.white
import com.falconteam.infoking.ui.viewmodels.LoginViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun BattleScreen(
    onClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    Background()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 36.dp)
                    .size(ElementResponsiveSize(size = 52.dp))
                    .clickable(onClick = {
                        showDialog = true
                    }),
                imageVector = Icons.Default.Info,
                contentDescription = "Info Button",
                tint = buttonOKColor
            )
        }
        Text(
            text = "TE ENCUENTRAS EN:",
            style = MaterialTheme.typography.bodyLarge,
            color = white,
            fontSize = TextResponsiveSize(size = 22.sp)
        )
        BattleCard(onClick = onClick)
        if (showDialog) {
            PopUpOneButtonDescription(
                onDismiss = { showDialog = false },
                onBack = {},
                titleText = "¿CÓMO JUGAR?",
                descriptionText = "Al dar click en el botón, se te presentarán dos opciones: atacar o pasar. Ambas utilizan 1 punto de energía.\n\nSi decides atacar al enemigo, ¡comienza a clickear repetidamente el botón para ganar la batalla!\n\nGanarás: Si la vida del enemigo llega a 0 o si llenas la barra de combate.\n\nPerderás: Si el enemigo lleva tu vida a 0 o lleva la barra de combate a 0.",
                buttonText = "¡A CLICKEAR!"
            )
        }
    }

}

@Composable
fun BattleCard(onClick: () -> Unit) {
    val context = LocalContext.current
    val viewLogin: LoginViewModel = viewModel()
    var noHealthPopUp by remember { mutableStateOf(false) }
    var noEnerygyPopUp by remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(secondaryBlueColor),
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 35.dp)
            .fillMaxWidth(0.9f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Text(
                text = "HOLA, MUNDO",
                color = white,
                fontSize = TextResponsiveSize(size = 24.sp),
                fontFamily = jostSemiBold,
                modifier = Modifier.padding(top = 45.dp)
            )
            Text(
                text = "Donde todo comenzó...",
                color = white,
                fontSize = TextResponsiveSize(size = 20.sp),
                fontFamily = jostRegular,
                modifier = Modifier.padding(top = 25.dp)
            )
            Button(
                onClick = {
                    val energia = runBlocking {
                        getData(
                            context, keyInt = PreferencesKeys.ENERGIA, type = 2
                        ).toString().toInt()
                    }
                    val health = runBlocking {
                        getData(
                            context, keyInt = PreferencesKeys.VIDA, type = 2
                        ).toString().toInt()
                    }
                    Log.d("uwu", health.toString())
                    if (energia > 0 && health > 30) {
                        runBlocking {
                            setData(
                                context,
                                IntKey = PreferencesKeys.ENERGIA,
                                dataInt = energia - 1,
                                type = 2
                            )
                        }
                        viewLogin.setStatsProfile(context)
                        onClick()
                    }
                    else if (health <= 30) {
                        noHealthPopUp = true
                    } else if (energia <= 0) {
                        noEnerygyPopUp = true
                    }
                },
                colors = ButtonDefaults.buttonColors(buttonCancelColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp, horizontal = 40.dp)
            ) {
                Text(
                    text = "BUSCAR ENEMIGO",
                    fontFamily = jostBold,
                    color = white,
                    fontSize = TextResponsiveSize(size = 20.sp)
                )
                if (noHealthPopUp) {
                    PopUpOneButtonDescription(
                        onDismiss = { noHealthPopUp = false },
                        onBack = {},
                        titleText = "¡NO TIENES VIDA!",
                        descriptionText = "Necesitas tener al menos 30 de vida para poder entrar en combate. Intenta de nuevo más tarde.",
                        buttonText = "CERRAR"
                    )
                } else if (noEnerygyPopUp) {
                    PopUpOneButtonDescription(
                        onDismiss = { noEnerygyPopUp = false },
                        onBack = {},
                        titleText = "¡NO TIENES ENERGÍA!",
                        descriptionText = "Necesitas tener al menos 1 punto de energía para poder entrar en combate. Intenta de nuevo más tarde.",
                        buttonText = "CERRAR"
                    )
                }
            }
        }

    }

}

@Preview
@Composable
fun PreviewBattleScreen() {
    BattleScreen(onClick = {})
}