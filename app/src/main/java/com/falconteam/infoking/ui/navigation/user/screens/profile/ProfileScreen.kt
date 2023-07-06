package com.falconteam.infoking.ui.navigation.user.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.falconteam.infoking.R
import com.falconteam.infoking.ui.components.ClearData
import com.falconteam.infoking.ui.components.ElementResponsiveSize
import com.falconteam.infoking.ui.components.PreferencesKeys.ATAQUE
import com.falconteam.infoking.ui.components.PreferencesKeys.DEFENSA
import com.falconteam.infoking.ui.components.PreferencesKeys.EXP
import com.falconteam.infoking.ui.components.PreferencesKeys.IMAGE_3D
import com.falconteam.infoking.ui.components.PreferencesKeys.NIVEL
import com.falconteam.infoking.ui.components.PreferencesKeys.USERNAME
import com.falconteam.infoking.ui.components.PreferencesKeys.VIDA
import com.falconteam.infoking.ui.components.TextResponsiveSize
import com.falconteam.infoking.ui.components.getData
import com.falconteam.infoking.ui.navigation.user.screens.tools.LoadingScreen
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.Typography
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.viewmodels.RankingViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {
    val viewModel: RankingViewModel = viewModel()
    InfoKingTheme {
        val context = LocalContext.current
        var logout by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(primaryColor)
                .padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "PERFIL",
                modifier = Modifier.padding(top = 40.dp, bottom = 12.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = secondaryAquaColor,
                fontSize = TextResponsiveSize(size = 40.sp)
            )
        viewModel.getPosition(context)
        if (viewModel.finished_profile.value && viewModel.dataRankingProfile.values != null) {
            var logout by remember {
                mutableStateOf(false)
            }

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                modifier = Modifier
                    .fillMaxSize()
                    .background(primaryColor)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column {
                    Text(
                        text = "PERFIL",
                        modifier = Modifier.padding(top = 52.dp, bottom = 12.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = secondaryAquaColor,
                        fontSize = TextResponsiveSize(size = 40.sp)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 28.dp)

                ) {
                    // Player's info
                    ProfileSection()
                    Spacer(modifier = Modifier.height(20.dp))

                    // Stats
                    StatsSection()
                    Spacer(modifier = Modifier.height(20.dp))


                    // Bonus
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row {
                            Column {
                                Text(
                                    text = "Total de victorias:",
                                    color = buttonOKColor,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = TextResponsiveSize(size = 20.sp)
                                )
                            }

                            Column {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "${viewModel.dataRankingProfile[0]?.victorias}",
                                    color = buttonOKColor,
                                    style = MaterialTheme.typography.bodySmall,
                                    textAlign = TextAlign.End,
                                    fontSize = TextResponsiveSize(size = 20.sp)
                                )
                            }
                        }
                        Row {
                            Column {
                                Text(
                                    text = "Total de derrotas:",
                                    color = buttonOKColor,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = TextResponsiveSize(size = 20.sp)
                                )
                            }

                            Column {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "${viewModel.dataRankingProfile[0]?.derrotas}",
                                    color = buttonOKColor,
                                    style = MaterialTheme.typography.bodySmall,
                                    textAlign = TextAlign.End,
                                    fontSize = TextResponsiveSize(size = 20.sp)
                                )
                            }
                        }

                        Row {
                            Column {
                                Text(
                                    text = "Puntos totales:",
                                    color = buttonOKColor,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = TextResponsiveSize(size = 20.sp)
                                )
                            }

                            Column {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "${viewModel.dataRankingProfile[0]?.score}",
                                    color = buttonOKColor,
                                    style = MaterialTheme.typography.bodySmall,
                                    textAlign = TextAlign.End,
                                    fontSize = TextResponsiveSize(size = 20.sp)
                                )
                            }
                        }
                        Row {
                            Column {
                                Text(
                                    text = "Posicion del ranking:",
                                    color = buttonOKColor,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = TextResponsiveSize(size = 20.sp)
                                )
                            }


                            Column {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "${viewModel.dataRankingProfile[0]?.position}",
                                    color = buttonOKColor,
                                    style = MaterialTheme.typography.bodySmall,
                                    textAlign = TextAlign.End,
                                    fontSize = TextResponsiveSize(size = 20.sp)
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                logout = true
                            },
                            colors = ButtonDefaults.buttonColors(secondaryAquaColor),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                        ) {
                            Text(
                                text = "CERRAR SESIÓN",
                                style = Typography.headlineSmall,
                                color = Color.White,
                                fontSize = TextResponsiveSize(size = 24.sp)
                            )
                        }
                    }
                }
            }
            if (logout) {
                ClearData(context = context)
                onLogout()
            }
        }
        else{
            LoadingScreen()
        }
    }
}

@Composable
fun ProfileSection() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .background(secondaryBlueColor.copy(alpha = 0.8f))
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = (runBlocking {
                    val nivel = getData(context, keyInt = NIVEL, type = 2) as? Int ?: 1
                    "Nivel $nivel"
                }),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(bottom = 16.dp),
                color = Color.White,
                fontSize = TextResponsiveSize(size = 18.sp)
            )

            AsyncImage(
                model = runBlocking {
                    getData(
                        context = context,
                        keyString = IMAGE_3D,
                    )
                },
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(0.6f)
                    .size(ElementResponsiveSize(size = 300.dp)),
            )

            Text(
                text = runBlocking {
                    getData(
                        context,
                        keyString = USERNAME
                    ).toString()
                },
                style = MaterialTheme.typography.headlineSmall,
                fontSize = TextResponsiveSize(size = 20.sp),
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 16.dp),
                color = Color.White,
            )

            LinearProgressIndicator(
                progress = (runBlocking {
                    val exp = getData(context, keyInt = EXP, type = 2) as? Float ?: 0f
                    val nivel = getData(context, keyInt = NIVEL, type = 2) as? Float ?: 1f
                    exp / (50f * nivel)
                }),
                color = buttonCancelColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
                    .padding(bottom = 12.dp)
                    .height(6.dp)
                    .clip(RoundedCornerShape(16.dp)),
                trackColor = secondaryAquaColor
            )

            Text(
                modifier = Modifier
                    .padding(bottom = 4.dp),
                text = (runBlocking {
                    val exp = getData(context, keyInt = EXP, type = 2) as? Int ?: 0
                    val nivel = getData(context, keyInt = NIVEL, type = 2) as? Int ?: 1
                    "$exp / ${50 * nivel}"
                }),
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                fontSize = TextResponsiveSize(size = 18.sp),
            )

            Text(
                text = "50 EXP necesario para subir de nivel",
                style = MaterialTheme.typography.labelMedium,
                color = secondaryAquaColor,
                fontSize = TextResponsiveSize(size = 20.sp),
            )
        }
    }
}

@Composable
fun StatsSection() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .background(secondaryBlueColor.copy(alpha = 0.8f))
                .padding(vertical = ElementResponsiveSize(size = 8.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    HealthStatCard()
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    DefenseStatCard()
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    AttackStatCard()
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    EnergyStatCard()
                }
            }
        }
    }
}

// Cards
@Composable
fun HealthStatCard() {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .size(ElementResponsiveSize(size = 40.dp)),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Ícono de Vida",
                    tint = secondaryAquaColor
                )
            }

            Column {
                Text(
                    text = (runBlocking {
                        val vida = getData(context, keyInt = VIDA, type = 2) as? Int ?: 1
                        "Vida: $vida"
                    }),
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = TextResponsiveSize(size = 20.sp)
                )
            }
        }
    }
}

@Composable
fun DefenseStatCard() {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    modifier = Modifier
                        .size(ElementResponsiveSize(size = 40.dp))
                        .padding(end = 6.dp),
                    imageVector = Icons.Default.Shield,
                    contentDescription = "Ícono de Defensa",
                    tint = secondaryAquaColor
                )
            }

            Column {
                Text(
                    text = (runBlocking {
                        val defensa = getData(context, keyInt = DEFENSA, type = 2) as? Int ?: 1
                        "Defensa: $defensa"
                    }),
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = TextResponsiveSize(size = 20.sp)
                )
            }
        }
    }
}

@Composable
fun AttackStatCard() {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.swords),
                    contentDescription = "Ícono de Ataque",
                    modifier = Modifier
                        .size(ElementResponsiveSize(size = 40.dp))
                        .padding(end = 8.dp)
                )
            }

            Column {
                Text(
                    text = (runBlocking {
                        val ataque = getData(context, keyInt = ATAQUE, type = 2) as? Int ?: 1
                        "Ataque: $ataque"
                    }),
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = TextResponsiveSize(size = 20.sp)
                )
            }
        }
    }
}

@Composable
fun EnergyStatCard() {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .size(ElementResponsiveSize(size = 40.dp)),
                    imageVector = Icons.Default.Bolt,
                    contentDescription = "Ícono de Energía",
                    tint = secondaryAquaColor
                )
            }

            Column {
                Text(
                    text = (runBlocking {
                        val energia = getData(context, keyInt = DEFENSA, type = 2) as? Int ?: 1
                        "Energía: $energia"
                    }),
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = TextResponsiveSize(size = 20.sp)
                )
            }
        }
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen(
        onLogout = {},
    )
}