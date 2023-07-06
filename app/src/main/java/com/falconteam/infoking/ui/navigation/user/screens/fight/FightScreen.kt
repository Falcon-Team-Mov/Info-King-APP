package com.falconteam.infoking.ui.navigation.user.screens.fight

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
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
import com.falconteam.infoking.data.models.SessionUserData
import com.falconteam.infoking.data.models.StatsUpdate
import com.falconteam.infoking.data.models.npc
import com.falconteam.infoking.data.network.dto.ranking.RankingRequest
import com.falconteam.infoking.ui.components.Background
import com.falconteam.infoking.ui.components.PreferencesKeys.ATAQUE
import com.falconteam.infoking.ui.components.PreferencesKeys.CREATED_AT
import com.falconteam.infoking.ui.components.PreferencesKeys.DEFENSA
import com.falconteam.infoking.ui.components.PreferencesKeys.EMAIL
import com.falconteam.infoking.ui.components.PreferencesKeys.ENERGIA
import com.falconteam.infoking.ui.components.PreferencesKeys.EXP
import com.falconteam.infoking.ui.components.PreferencesKeys.ID
import com.falconteam.infoking.ui.components.PreferencesKeys.IMAGE_2D
import com.falconteam.infoking.ui.components.PreferencesKeys.NIVEL
import com.falconteam.infoking.ui.components.PreferencesKeys.PERSONAJE_ID
import com.falconteam.infoking.ui.components.PreferencesKeys.ROLE
import com.falconteam.infoking.ui.components.PreferencesKeys.TIME_PLAYING
import com.falconteam.infoking.ui.components.PreferencesKeys.USERNAME
import com.falconteam.infoking.ui.components.PreferencesKeys.VIDA
import com.falconteam.infoking.ui.components.PreferencesKeys._ID
import com.falconteam.infoking.ui.components.PreferencesKeysBattle.ID_NPC
import com.falconteam.infoking.ui.components.attackgenerator
import com.falconteam.infoking.ui.components.generateRandomNumber
import com.falconteam.infoking.ui.components.getCurrentDateTime
import com.falconteam.infoking.ui.components.getData
import com.falconteam.infoking.ui.components.getDataBattle
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FightScreen(
    data: npc,
    onBack: () -> Unit
) {
    com.falconteam.infoking.ui.theme.InfoKingTheme() {
        Background()
        val viewModel: AttackViewModel = viewModel()

        var player by remember { mutableStateOf(0f) }
        var enemy by remember { mutableStateOf(0f) }
        var progress by remember { mutableStateOf(0f) }
        var activated by remember { mutableStateOf(false) }
        var finished by remember { mutableStateOf(false) }
        val current = LocalContext.current
        val last_conection = getCurrentDateTime()
        var enviado by remember { mutableStateOf(false) }

        var vida by remember {
            mutableStateOf(runBlocking {
                getData(current, keyInt = VIDA, type = 2).toString().toInt()
            })
        }

        GlobalScope.launch(Dispatchers.Main) {
            while (!finished) {

                if (activated) {
                    enemy = runBlocking {
                        attackgenerator(
                            data.ataque,
                            runBlocking {
                                getData(current, keyInt = DEFENSA, type = 2).toString().toInt()
                            }
                        )
                    }
                    if (enemy > 0) {
                        progress -= enemy / 100f
                        setData(current, IntKey = VIDA, dataInt = vida - 1, type = 2)
                        vida -= 1
                    } else {
                        progress -= 0f
                    }
                    if (progress >= 1f || data.vida <= 0 || vida <= 0 || progress <= 0f) {
                        finished = true
                        if (activated && progress >= 1f) Toast.makeText(
                            current,
                            "GANASTE",
                            Toast.LENGTH_SHORT
                        ).show() else if (activated && progress <= 0f) Toast.makeText(
                            current,
                            "PERDISTE",
                            Toast.LENGTH_SHORT
                        ).show()
                        activated = false

                    }
                }

                kotlinx.coroutines.delay(400)
            }
        }
        androidx.compose.foundation.layout.Column(
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
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "CIUDAD INICIAL",
                    fontFamily = jostSemiBold,
                    fontSize = 18.sp,
                    color = buttonOKColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 75.dp, bottom = 45.dp)
                )
                Card(
                    colors = CardDefaults.cardColors(secondaryBlueColor),
                    modifier = Modifier
                        .fillMaxSize(0.8f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()

                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.8f),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            androidx.compose.foundation.layout.Column() {
                                FightItemCharacter(vida)
                            }
                            androidx.compose.foundation.layout.Column() {
                                FightItemEnemy(data = data)
                            }
                        }

                        LinearProgressIndicator(
                            progress = progress,
                            color = buttonCancelColor,
                            trackColor = buttonOKColor,
                            modifier = Modifier
                                .fillMaxHeight(0.1f)
                                .fillMaxWidth(0.8f)
                                .padding(bottom = 32.dp)
                                .clip(RoundedCornerShape(10.dp))

                        )
                    }
                }
                Button(
                    onClick = {
                        if (!finished) {
                            Log.d("Prueba", "$progress")
                            if (progress > 0.1f) {
                                activated = true
                            }
                            player = runBlocking {
                                attackgenerator(
                                    runBlocking {
                                        getData(current, keyInt = ATAQUE, type = 2).toString()
                                            .toInt()
                                    },
                                    data.defensa
                                )
                            }
                            if (player > 0f) {
                                progress += player / 100f
                                data.vida -= 1
                            } else {
                                progress += 0f

                            }
                            if (progress >= 1f || data.vida <= 0 || vida <= 0 || progress <= 0f) {
                                finished = true
                                if (activated && progress >= 1f) Toast.makeText(
                                    current,
                                    "GANASTE",
                                    Toast.LENGTH_SHORT
                                ).show() else if (activated && progress <= 0f) Toast.makeText(
                                    current,
                                    "PERDISTE",
                                    Toast.LENGTH_SHORT
                                ).show()
                                activated = false

                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(secondaryAquaColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 45.dp, horizontal = 45.dp)
                        .size(width = 221.dp, height = 40.dp)
                ) {
                    Text(
                        text = "ATACAR",
                        fontFamily = jostBold,
                        fontSize = 16.sp,
                        color = white
                    )
                }
            }
            if (finished && !enviado) {
                if (progress >= 1f) {
                    val exp = runBlocking {
                        getData(current, keyInt = EXP, type = 2).toString().toInt() + 1
                    }
                    val nivel = runBlocking {
                        getData(current, keyInt = NIVEL, type = 2).toString().toInt()
                    }
                    val vida = runBlocking {
                        getData(current, keyInt = VIDA, type = 2).toString().toInt()
                    }
                    val ataque = runBlocking {
                        getData(current, keyInt = ATAQUE, type = 2).toString().toInt()
                    }
                    val defensa = runBlocking {
                        getData(current, keyInt = DEFENSA, type = 2).toString().toInt()
                    }
                    android.util.Log.d("Pruebas", "FightScreen: ${exp > (50 * nivel)}")
                    if (exp > (50 * nivel)) {
                        setData(current, IntKey = EXP, dataInt = 0, type = 2)
                        setData(current, IntKey = NIVEL, dataInt = nivel + 1, type = 2)
                        setData(
                            current,
                            IntKey = VIDA,
                            dataInt = vida + runBlocking { generateRandomNumber(vida / 4) },
                            type = 2
                        )
                        setData(
                            current,
                            IntKey = ATAQUE,
                            dataInt = ataque + runBlocking { generateRandomNumber(ataque / 4) },
                            type = 2
                        )
                        setData(
                            current,
                            IntKey = DEFENSA,
                            dataInt = defensa + runBlocking { generateRandomNumber(defensa / 4) },
                            type = 2
                        )
                    } else {
                        setData(current, IntKey = EXP, dataInt = exp + 1, type = 2)
                    }
                    viewModel.putVictoryRanking(
                        RankingRequest(
                            runBlocking {
                                getData(current, keyString = ID, type = 1).toString()
                            },
                            runBlocking {
                                getData(current, keyString = PERSONAJE_ID, type = 1).toString()
                            },
                            runBlocking {
                                getDataBattle(current, keyString = ID_NPC, type = 1).toString()
                            },
                        )
                    )
                    Log.d("Prueba", "${
                        runBlocking {
                            getDataBattle(current, keyString = ID_NPC, type = 1).toString()
                        }
                    }")
                } else {
                    viewModel.putDerrotRanking(
                        RankingRequest(
                            runBlocking {
                                getData(current, keyString = ID, type = 1).toString()
                            },
                            runBlocking {
                                getData(current, keyString = PERSONAJE_ID, type = 1).toString()
                            },
                        )
                    )
                }
                val user = SessionUserData(
                    runBlocking {
                        getData(current, keyString = ID, type = 1).toString()
                    },
                    runBlocking {
                        getData(current, keyString = USERNAME, type = 1).toString()
                    },
                    runBlocking {
                        getData(current, keyString = EMAIL, type = 1).toString()
                    },
                    runBlocking {
                        getData(current, keyString = ROLE, type = 1).toString()
                    },
                    runBlocking {
                        getData(current, keyInt = EXP, type = 2).toString().toInt()
                    },
                    runBlocking {
                        getData(current, keyInt = NIVEL, type = 2).toString().toInt()
                    },
                    last_conection,
                    runBlocking {
                        getData(current, keyString = CREATED_AT, type = 1).toString()
                            .toString()
                    },
                    runBlocking {
                        getData(current, keyInt = TIME_PLAYING, type = 2).toString().toInt()
                    },
                )
                Log.d("Prueba", "$user")
                viewModel.putUser(
                    runBlocking {
                        getData(current, keyString = ID, type = 1).toString()
                    },
                    user
                )
                viewModel.putStats(
                    runBlocking {
                        getData(current, keyString = _ID, type = 1).toString()
                    },
                    StatsUpdate(
                        runBlocking {
                            getData(current, keyString = PERSONAJE_ID, type = 1).toString()
                        },
                        runBlocking {
                            getData(current, keyInt = VIDA, type = 2).toString().toInt()
                        },
                        runBlocking {
                            getData(current, keyInt = ATAQUE, type = 2).toString().toInt()
                        },
                        runBlocking {
                            getData(current, keyInt = DEFENSA, type = 2).toString().toInt()
                        },
                        runBlocking {
                            getData(current, keyInt = ENERGIA, type = 2).toString().toInt()
                        },
                    )
                )
                enviado = true
                onBack()
            }
        }
    }
}

@Composable
fun FightItemCharacter(vida: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.9f)
    ) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(0.8f),
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
                modifier = Modifier
                    .padding(top = 12.dp)

            )
            LinearProgressIndicator(
                progress = vida / 100f,
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
fun FightItemEnemy(data: npc) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.9f)
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
                modifier = Modifier
                    .padding(top = 12.dp)
            )
            LinearProgressIndicator(
                progress = data.vida.toFloat() / 100f,
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
        modifier = Modifier
            .fillMaxWidth(0.7f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
        ) {
            Text(
                text = "Ataque",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "Defensa:",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "Vida:",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
        ) {
            val context = LocalContext.current
            Text(
                text = runBlocking {
                    getData(context, keyInt = ATAQUE, type = 2).toString()
                },
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = runBlocking {
                    getData(context, keyInt = DEFENSA, type = 2).toString()
                },
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = vida.toString(),
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
    }
}

@Composable
fun Fightdetail2(data: npc) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(0.7f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
        ) {
            Text(
                text = "Ataque",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "Defensa:",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "Vida:",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
        ) {
            Text(
                text = data.ataque.toString(),
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = data.defensa.toString(),
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = data.vida.toString(),
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
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
            "",
            "",
            0,
            0,
            0,
            ""
        ),
        onBack = {}
    )
}