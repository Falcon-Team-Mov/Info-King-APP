package com.falconteam.infoking.ui.navigation.user.screens.inventory

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.infoking.R
import com.falconteam.infoking.ui.components.ElementResponsiveSize
import com.falconteam.infoking.ui.components.TextResponsiveSize
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor

@Composable
fun InventoryScreen(modifier: Modifier = Modifier) {
    InfoKingTheme() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(primaryColor)
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .verticalScroll(rememberScrollState()),

        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.7f)
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "INVENTARIO",
                    modifier = Modifier.padding(top = 52.dp, bottom = 40.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = secondaryAquaColor,
                    fontSize = TextResponsiveSize(size = 40.sp)
                )
                InventoryCardCafe()
                InventoryCardFuego()
                InventoryCardLentes()
                InventoryCardAtaque()

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        "¿Cómo funcionan los poderes?",
                        modifier = Modifier.padding(top = 50.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = buttonOKColor,
                        fontSize = TextResponsiveSize(size = 32.sp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Al dar click en el ícono de cada poder, este se activará por una cantidad aleatoria de batallas (de 1 a 3)",
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 44.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = buttonOKColor,
                        textAlign = TextAlign.Center,
                        fontSize = TextResponsiveSize(size = 21.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun InventoryCardCafe() {
    val sizeIcon = ElementResponsiveSize(size = 64.dp)
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.14f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cup),
                        contentDescription = "Cafe",
                        modifier = Modifier.size(width = sizeIcon, height = sizeIcon),
                    )

                    Text(
                        text = "1/%",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 11.dp),
                        fontSize = TextResponsiveSize(size = 22.sp)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(0.7f)
                ) {
                    Text(
                        text = "Café: 0/3",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = TextResponsiveSize(size = 24.sp)
                    )

                    Text(
                        text = "Recupera parte de tu vida",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = TextResponsiveSize(size = 20.sp)
                    )
                }
                Icon(
                    imageVector = Icons.Filled.Add,
                    "Agregar",
                    tint = buttonOKColor,
                    modifier = Modifier
                        .size(width = sizeIcon, height = sizeIcon)
                )
            }
        }
    }
}

@Composable
fun InventoryCardFuego() {
    val sizeIcon = ElementResponsiveSize(size = 64.dp)
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.15f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.firewall),
                        contentDescription = "Corta Fuego",
                        modifier = Modifier.size(width = sizeIcon, height = sizeIcon),
                    )

                    Text(
                        text = "1/%",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 11.dp),
                        fontSize = TextResponsiveSize(size = 22.sp)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(0.7f),
                    horizontalAlignment = Alignment.Start

                ) {
                    Text(
                        text = "Corta Fuego: 0/3",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = TextResponsiveSize(size = 24.sp)
                    )

                    Text(
                        text = "Aumenta la defensa",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = TextResponsiveSize(size = 20.sp)
                    )
                }
                Icon(
                    imageVector = Icons.Filled.Add,
                    "Agregar",
                    tint = buttonOKColor,
                    modifier = Modifier
                        .size(width = sizeIcon, height = sizeIcon)
                )
            }
        }
    }
}

@Composable
fun InventoryCardLentes() {
    val sizeIcon = ElementResponsiveSize(size = 64.dp)
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(top = 35.dp)
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.2f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lentes),
                        contentDescription = "Imagen de lentes",
                        modifier = Modifier.size(sizeIcon),
                    )

                    Text(
                        text = "1/%",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(horizontal = 11.dp),
                        fontSize = TextResponsiveSize(size = 22.sp)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .fillMaxWidth(0.7f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Lentes: 0/3",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = TextResponsiveSize(size = 24.sp)
                    )

                    Text(
                        text = "Aumenta el ataque",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = TextResponsiveSize(size = 20.sp)
                    )
                }
                Icon(
                    imageVector = Icons.Filled.Add,
                    "Agregar",
                    tint = buttonOKColor,
                    modifier = Modifier
                        .size(width = sizeIcon, height = sizeIcon)
                )
            }
        }
    }
}

@Composable
fun InventoryCardAtaque() {
    val sizeIcon = ElementResponsiveSize(size = 64.dp)
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(top = 35.dp)
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.5f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.atttack),
                        contentDescription = "Imagen de ataque",
                        modifier = Modifier.size(width = sizeIcon, height = sizeIcon),
                    )
                    Text(
                        text = "1/%",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 11.dp),
                        fontSize = TextResponsiveSize(size = 22.sp)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .fillMaxWidth(0.7f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Doble Ataque: 0/3",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = TextResponsiveSize(size = 24.sp)
                    )

                    Text(
                        text = "Posibilidad de atacar dos veces",
                        color = buttonOKColor,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = TextResponsiveSize(size = 20.sp)
                    )
                }
                Icon(
                    imageVector = Icons.Filled.Add,
                    "Agregar",
                    tint = buttonOKColor,
                    modifier = Modifier
                        .size(width = sizeIcon, height = sizeIcon)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewRankingScreen() {
    InventoryScreen()
}