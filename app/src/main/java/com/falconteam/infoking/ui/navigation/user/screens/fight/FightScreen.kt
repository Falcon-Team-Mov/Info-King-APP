package com.falconteam.infoking.ui.navigation.user.screens.fight

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.falconteam.infoking.data.network.dto.ranking.RankingRequest
import com.falconteam.infoking.ui.components.Background
import com.falconteam.infoking.ui.components.PopUpOneButton
import com.falconteam.infoking.ui.components.PreferencesKeys
import com.falconteam.infoking.ui.components.PreferencesKeys.ATAQUE
import com.falconteam.infoking.ui.components.PreferencesKeys.BATTLE_ACTIVE
import com.falconteam.infoking.ui.components.PreferencesKeys.DEFENSA
import com.falconteam.infoking.ui.components.PreferencesKeys.ID
import com.falconteam.infoking.ui.components.PreferencesKeys.IMAGE_2D
import com.falconteam.infoking.ui.components.PreferencesKeys.MAX_VIDA
import com.falconteam.infoking.ui.components.PreferencesKeys.PERSONAJE_ID
import com.falconteam.infoking.ui.components.PreferencesKeys.USERNAME
import com.falconteam.infoking.ui.components.PreferencesKeys.VIDA
import com.falconteam.infoking.ui.components.PreferencesKeysBattle.ID_NPC
import com.falconteam.infoking.ui.components.attackSystem
import com.falconteam.infoking.ui.components.calculateLevel
import com.falconteam.infoking.ui.components.getData
import com.falconteam.infoking.ui.components.getDataBattle
import com.falconteam.infoking.ui.components.saveData
import com.falconteam.infoking.ui.components.setData
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.jostBold
import com.falconteam.infoking.ui.theme.jostRegular
import com.falconteam.infoking.ui.theme.jostSemiBold
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.theme.white
import com.falconteam.infoking.ui.viewmodels.AttackViewModel
import com.falconteam.infoking.ui.viewmodels.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FightScreen(
    data: npc, onBack: () -> Unit
) {
    com.falconteam.infoking.ui.theme.InfoKingTheme() {
        Background()

        val viewModel: AttackViewModel = viewModel()

        var player by remember { mutableStateOf(0f) }
        var enemy by remember { mutableStateOf(0f) }
        var ataque by remember { mutableStateOf(0f) }
        var progress by remember { mutableStateOf(0f) }
        var activated by remember { mutableStateOf(false) }
        var finished by remember { mutableStateOf(false) }
        val current = LocalContext.current
        var enviado by remember { mutableStateOf(false) }
        var showDialog by remember { mutableStateOf(false) }
        var win by remember { mutableStateOf(false) }

        var vida by remember {
            mutableStateOf(runBlocking {
                getData(current, keyInt = VIDA, type = 2).toString().toInt()
            })
        }
        val maxvida = runBlocking {
            getData(current, keyInt = MAX_VIDA, type = 2).toString().toInt()
        }

        val maxvidanpc = data.vida

        val job = GlobalScope.launch(Dispatchers.Main) {
            while (!finished) {

                if (activated) {
                    val PlayerDefense = runBlocking {
                        getData(current, keyInt = DEFENSA, type = 2).toString().toInt()
                    }
                    vida = runBlocking {
                        getData(current, keyInt = VIDA, type = 2).toString().toInt()
                    }

                    progress -= attackSystem(current, PlayerDefense, data.ataque, ((runBlocking {
                        getData(current, keyInt = VIDA, type = 2).toString().toInt()
                    } + data.vida).toFloat()) * 8f,
                        vida = vida)
                }
                if (progress >= 1f || data.vida <= 0 || vida <= 0 || progress < 0f) {
                    finished = true
                    if (activated && (progress >= 1f || data.vida <= 0)) {
                        showDialog = true
                        win = true
                    } else if (activated && (progress <= 0f || vida <= 0)) {
                        showDialog = true
                        win = false
                    }
                    activated = false
                }
                delay(200)
            }
        }
        BackHandler() {
            job.cancel()
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(androidx.compose.ui.graphics.Color.Transparent)
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "CIUDAD INICIAL",
                    fontFamily = jostSemiBold,
                    fontSize = 18.sp,
                    color = buttonOKColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 75.dp, bottom = 24.dp)
                )
                Card(
                    colors = CardDefaults.cardColors(secondaryBlueColor),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column {
                                FightItemCharacter(vida, maxvida)
                            }
                            Column {
                                FightItemEnemy(data = data, maxvidanpc = maxvidanpc)
                            }
                        }

                        LinearProgressIndicator(
                            progress = progress,
                            color = buttonCancelColor,
                            trackColor = buttonOKColor,
                            modifier = Modifier
                                .fillMaxHeight(0.1f)
                                .fillMaxWidth(0.8f)
                                .padding(top = 16.dp, bottom = 28.dp)
                                .clip(RoundedCornerShape(10.dp))

                        )
                    }
                }
                Button(
                    onClick = {

                        if (!finished) {
                            setData(
                                current, BooleanKey = BATTLE_ACTIVE, dataBoolean = true, type = 4
                            )
                            if (progress > 0.1f) {
                                activated = true
                            }
                            if (data.vida > 0 || vida > 0) {
                                val attack = runBlocking {
                                    getData(
                                        current, keyInt = ATAQUE, type = 2
                                    ).toString().toInt()
                                }
                                val totalAttack = ((runBlocking {
                                    getData(current, keyInt = VIDA, type = 2).toString().toInt()
                                } + data.vida).toFloat()) * 2f
                                val obtainAttack = attackSystem(
                                    current,
                                    data.defensa,
                                    attack,
                                    totalAttack,
                                    player = true
                                )

                                if ((obtainAttack * totalAttack) - (data.defensa / 2) > 0) {
                                    data.vida -= 1
                                }

                                progress += obtainAttack
                            }

                            if (progress >= 1f || data.vida <= 0 || vida <= 0 || progress < 0f) {
                                finished = true
                                if (activated && (progress >= 1f || data.vida <= 0)) {
                                    showDialog = true
                                    win = true
                                } else if (activated && (progress <= 0f || vida <= 0)) {
                                    showDialog = true
                                    win = false
                                }
                                activated = false
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(secondaryAquaColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .padding(horizontal = 45.dp)
                        .size(width = 221.dp, height = 40.dp)
                ) {
                    Text(
                        text = "ATACAR", fontFamily = jostBold, fontSize = 16.sp, color = white
                    )
                }
            }
            if (finished && !enviado) {
                enviado = true
            } else if (finished) {
                if (showDialog) {
                    if (win) {
                        val viewLogin: LoginViewModel = viewModel()
                        PopUpOneButton(
                            onDismiss = {
                                showDialog = false
                                calculateLevel(current, data.nivel, viewLogin)
                                viewModel.putVictoryRanking(
                                    RankingRequest(
                                        runBlocking {
                                            getData(
                                                current, keyString = ID, type = 1
                                            ).toString()
                                        },
                                        runBlocking {
                                            getData(
                                                current, keyString = PERSONAJE_ID, type = 1
                                            ).toString()
                                        },
                                        runBlocking {
                                            getDataBattle(
                                                current, keyString = ID_NPC, type = 1
                                            ).toString()
                                        },
                                    )
                                )
                                saveData(current, viewModel)

                                setData(
                                    current,
                                    dataBoolean = false,
                                    BooleanKey = PreferencesKeys.BATTLE_ACTIVE,
                                    type = 4
                                )
                            },
                            onBack = { onBack() },
                            titleText = "¡HAS GANADO!",
                            buttonText = "CERRAR"
                        )
                    } else if (!win) {
                        PopUpOneButton(
                            onDismiss = {
                                viewModel.putDerrotRanking(
                                    RankingRequest(
                                        runBlocking {
                                            getData(
                                                current, keyString = ID, type = 1
                                            ).toString()
                                        },
                                        runBlocking {
                                            getData(
                                                current, keyString = PERSONAJE_ID, type = 1
                                            ).toString()
                                        },
                                    )
                                )
                                saveData(current, viewModel)

                                setData(
                                    current,
                                    dataBoolean = false,
                                    BooleanKey = PreferencesKeys.BATTLE_ACTIVE,
                                    type = 4
                                )
                                showDialog = false
                            },
                            onBack = { onBack() },
                            titleText = "¡HAS PERDIDO!",
                            buttonText = "CERRAR"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FightItemCharacter(vida: Int, maxVida: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.9f)
            .padding(top = 16.dp)
    ) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(0.8f)
        ) {
            AsyncImage(
                model = runBlocking {
                    getData(context, IMAGE_2D)
                },
                contentDescription = "Jugador",
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(1f),
        ) {
            Text(
                text = runBlocking {
                    getData(context, USERNAME).toString()
                },
                color = white,
                fontFamily = jostSemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 12.dp)

            )
            val total = runBlocking {
                getData(context, keyInt = VIDA, type = 2).toString().toInt()
            }.toFloat()
            val current = if (total > 0 && maxVida > 0) vida.toFloat() / maxVida else 0f
            LinearProgressIndicator(
                progress = current,
                color = buttonCancelColor,
                trackColor = buttonOKColor,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxHeight(0.05f)
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(10.dp))

            )
            Fightdetail(vida)
        }
    }

}

@Composable
fun FightItemEnemy(data: npc, maxvidanpc: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.9f)
            .padding(top = 18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(0.8f),
        ) {
            AsyncImage(
                model = data.imagen,
                contentDescription = "NPC",
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(0.8f)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(1f),

            ) {
            Text(
                text = data.nombre,
                color = white,
                fontFamily = jostSemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 12.dp)
            )
            var current =
                if (data.vida > 0 && maxvidanpc > 0) (data.vida.toFloat() / maxvidanpc) else 0f
            LinearProgressIndicator(
                progress = current,
                color = buttonCancelColor,
                trackColor = buttonOKColor,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxHeight(0.05f)
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(10.dp))

            )
            Fightdetail2(data)
        }
    }

}

@Composable
fun Fightdetail(vida: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(0.7f)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        ) {
            val context = LocalContext.current
            Text(
                text = "Ataque:",
                fontFamily = jostSemiBold,
                color = white,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = runBlocking {
                    getData(context, keyInt = ATAQUE, type = 2).toString()
                }, fontFamily = jostRegular, color = white, modifier = Modifier
            )

            Text(
                text = "Defensa:",
                fontFamily = jostSemiBold,
                color = white,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = runBlocking {
                    getData(context, keyInt = DEFENSA, type = 2).toString()
                }, fontFamily = jostRegular, color = white, modifier = Modifier
            )

            Text(
                text = "Vida:",
                fontFamily = jostSemiBold,
                color = white,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = vida.toString(), fontFamily = jostRegular, color = white, modifier = Modifier
            )
        }
    }
}

@Composable
fun Fightdetail2(data: npc) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(0.7f)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        ) {
            Text(
                text = "Ataque:",
                fontFamily = jostSemiBold,
                color = white,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = data.ataque.toString(),
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
            )

            Text(
                text = "Defensa:",
                fontFamily = jostSemiBold,
                color = white,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = data.defensa.toString(),
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
            )

            Text(
                text = "Vida:",
                fontFamily = jostSemiBold,
                color = white,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = data.vida.toString(),
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewFightScreen() {
    FightScreen(
        data = npc(
            "", "", 0, 0, 0, 0, ""
        ), onBack = {})
}