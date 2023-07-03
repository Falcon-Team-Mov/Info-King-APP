package com.falconteam.infoking.ui.navigation.user.screens.attack

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.falconteam.infoking.data.models.npc
import com.falconteam.infoking.ui.components.Background
import com.falconteam.infoking.ui.components.PreferencesKeys
import com.falconteam.infoking.ui.components.TextResponsiveSize
import com.falconteam.infoking.ui.components.getData
import com.falconteam.infoking.ui.navigation.user.screens.tools.LoadingScreen
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.jostBold
import com.falconteam.infoking.ui.theme.jostRegular
import com.falconteam.infoking.ui.theme.jostSemiBold
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.white
import com.falconteam.infoking.ui.viewmodels.AttackViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun AttackScreen(
    onBack: () -> Unit,
    onAttack: (data: npc) -> Unit,
) {
    InfoKingTheme {
        val viewModel: AttackViewModel = viewModel()
        val context = LocalContext.current
        LaunchedEffect(Unit) {
            viewModel.getNPCAll((runBlocking {
                getData(context, keyString = PreferencesKeys.ID)
            } as String))
        }
        if (!viewModel.finished.value) {
            LoadingScreen()
        } else {
            if (viewModel.errors.value) {
                onBack()
            } else {
                Background()
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxHeight(0.1f)
                    ) {
                        Text(
                            text = "CIUDAD INICIAL",
                            color = buttonOKColor,
                            fontFamily = jostSemiBold,
                            fontSize = TextResponsiveSize(32.sp),
                            modifier = Modifier
                                .padding(vertical = 65.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .fillMaxHeight(0.4f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        AsyncImage(
                            model = viewModel.data[0]?.imagen,
                            contentDescription = "NPC",
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(0.6f)
                                .fillMaxHeight(0.4f)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        LinearProgressIndicator(
                            progress = viewModel.data[0]?.vida?.toFloat()!! / viewModel.data[0]?.vida?.toFloat()!!,
                            color = buttonCancelColor,
                            trackColor = buttonOKColor,
                            modifier = Modifier
                                .padding(top = 35.dp)
                                .fillMaxWidth(0.6f)
                                .clip(RoundedCornerShape(10.dp))

                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Vida: ${viewModel.data[0]?.vida} / ${viewModel.data[0]?.vida}",
                            fontFamily = jostRegular,
                            fontSize = TextResponsiveSize(16.sp),
                            color = white,
                            modifier = Modifier
                                .padding(top = 10.dp)
                        )
                    }
                    AttackCard(
                        data = viewModel.data[0],
                        viewModel = viewModel,
                        context = context,
                        onAttack = onAttack
                    )
                }
            }
        }
    }
}


@Composable
fun AttackCard(
    data: npc? = null,
    viewModel: AttackViewModel,
    context: Context,
    onAttack: (data: npc) -> Unit
) {
    val opacity = 0.7f
    val sizeFont = TextResponsiveSize(16.sp)
    Card(
        colors = CardDefaults.cardColors(primaryColor.copy(alpha = opacity)),
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 35.dp)
            .fillMaxHeight(0.8f)
            .fillMaxWidth(0.85f),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Te has encontrado con un NPC con ${data?.vida ?: 0} de vida, \n" +
                        "${data?.ataque} de ataque y ${data?.defensa} de defensa. \n" +
                        "¿Deseas atacarlo?",
                color = white,
                fontSize = sizeFont,
                fontFamily = jostSemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 20.dp)

            )
            Button(
                onClick = {
                    onAttack(data!!)
                },
                colors = ButtonDefaults.buttonColors(secondaryAquaColor),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = "ATACAR",
                    fontFamily = jostBold,
                    fontSize = sizeFont,
                    color = white,
                )
            }
            Button(
                onClick = {
                    viewModel.getNPCAll((runBlocking {
                        getData(context = context, keyString = PreferencesKeys.ID)
                    } as String))
                },
                colors = ButtonDefaults.buttonColors(secondaryAquaColor),
                modifier = Modifier
                    .fillMaxWidth(0.8f)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Filled.Bolt,
                        "Rayito bonito",
                        tint = white
                    )
                    Text(
                        text = "PASAR",
                        fontFamily = jostBold,
                        fontSize = sizeFont,
                        color = white,
                    )
                }
            }

        }

    }

}

@Preview
@Composable
fun PreviewAttackScreen() {
    AttackScreen(
        onBack = {},
        onAttack = {}
    )
}