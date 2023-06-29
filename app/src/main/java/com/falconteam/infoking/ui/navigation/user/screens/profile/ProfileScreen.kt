package com.falconteam.infoking.ui.navigation.user.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.infoking.R
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor

@Composable
fun ProfileScreen() {
    InfoKingTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(primaryColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column {
                Text(
                    text = "PERFIL",
                    modifier = Modifier.padding(top = 60.dp, bottom = 12.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = secondaryAquaColor
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
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
                        .height(80.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                    Row {
                        Column {
                            Text(
                                text = "Bonus por ranking:",
                                color = buttonOKColor,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "{%%}",
                                color = buttonOKColor,
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                    Row {
                        Column {
                            Text(
                                text = "Bonus por personaje:",
                                color = buttonOKColor,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "{%%} de {habilidad}",
                                color = buttonOKColor,
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.End
                            )
                        }
                    }

                    Row {
                        Column {
                            Text(
                                text = "Bonus por poder:",
                                color = buttonOKColor,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "{%%} de {habilidad}",
                                color = buttonOKColor,
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileSection() {
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
                text = "Nivel 1",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(bottom = 16.dp),
                color = Color.White
            )

            Image(
                painter = painterResource(id = R.drawable.nestor),
                contentDescription = "Player Character",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(164.dp)
            )

            Text(
                text = "Marroquin02",
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp),
                color = Color.White
            )

            LinearProgressIndicator(
                progress = 0.3f,
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
                text = "EXP 50/100",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White
            )

            Text(
                text = "50 EXP necesario para subir de nivel",
                fontSize = 12.sp,
                style = MaterialTheme.typography.labelMedium,
                color = secondaryAquaColor
            )
        }
    }
}

@Composable
fun StatsSection() {
    val composableList: List<@Composable () -> Unit> = listOf(
        { HealthStatCard() },
        { DefenseStatCard() },
        { AttackStatCard() },
        { EnergyStatCard() }
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .background(secondaryBlueColor.copy(alpha = 0.8f)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            LazyVerticalGrid(
                modifier = Modifier,
                columns = GridCells.Fixed(2),
                content = {
                    items(composableList.size) { index ->
                        composableList[index]()
                    }
                },
                userScrollEnabled = false,
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center
            )
        }
    }
}

// Cards
@Composable
fun HealthStatCard() {
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Ícono de Vida",
                    tint = secondaryAquaColor
                )
            }

            Column {
                Text(
                    text = "Vida: 100",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun DefenseStatCard() {
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = "Ícono de Defensa",
                    tint = secondaryAquaColor
                )
            }

            Column {
                Text(
                    text = "Defensa: 100",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun AttackStatCard() {
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.swords),
                    contentDescription = "Ícono de Ataque",
                    modifier = Modifier.size(24.dp)
                )
            }

            Column {
                Text(
                    text = "Ataque: 100",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun EnergyStatCard() {
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = "Ícono de Energía",
                    tint = secondaryAquaColor,
                    modifier = Modifier.size(28.dp)
                )
            }

            Column {
                Text(
                    text = "Energía: 100",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen()
}