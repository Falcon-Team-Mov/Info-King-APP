package com.falconteam.infoking.ui.navigation.user.screens.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor

@Composable
fun InventoryScreen( modifier: Modifier = Modifier){
//Agregue el InfoKingTheme que incluye el tama;o de forma dinamica y agregue un fillMaxSize para que se adapte
    InfoKingTheme() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(primaryColor)
                .fillMaxSize()

        ) {
            Text(
                "INVENTARIO",
                modifier = Modifier.padding(top = 60.dp, bottom = 50.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = secondaryAquaColor,
            )
            InventoryCardCafe()
            InventoryCardFuego()
            InventoryCardLentes()
            InventoryCardAtaque()

            Text(
                "¿Cómo funcionan los poderes?",
                modifier = Modifier.padding(top = 50.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = buttonOKColor,
            )
            Text(
                "Al dar click en el ícono de cada poder, este se activará por una cantidad aleatoria de batallas (de 1 a 3)",
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 40.dp),
                style = MaterialTheme.typography.bodySmall,
                color = buttonOKColor,
                textAlign = TextAlign.Center
            )

        }
    }
}

@Composable
fun InventoryCardCafe(){
    Card(colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(top = 25.dp)
            .size(width = 312.dp, height = 72.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.cafe),
                        contentDescription = "Imagen de cafe",
                        modifier = Modifier.size(width = 50.dp, height = 50.dp))
                    Text(
                        text = "1%",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 11.dp),
                    )
                }

                Column() {
                    Text(
                        text = "Café:",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Recupera parte de tu vida      ",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Image(painter = painterResource(id = R.drawable.plus),
                    contentDescription = "Imagen de cafe",
                    modifier = Modifier.size(width = 28.dp, height = 28.dp),
                    colorFilter = ColorFilter.tint(buttonOKColor) )
            } }
    }
}

@Composable
fun InventoryCardFuego() {
    Card(colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(top= 35.dp)
            .size(width = 312.dp, height = 72.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.corta_fuego),
                        contentDescription = "Imagen de cafe",
                        modifier = Modifier.size(width = 50.dp, height = 50.dp))
                    Text(
                        text = "1%",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 11.dp),
                    )
                }

                Column() {
                    Text(
                        text = "Corta Fuego: 0/3",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Aumenta la defensa               ",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Image(painter = painterResource(id = R.drawable.plus),
                    contentDescription = "Imagen de cafe",
                    modifier = Modifier.size(width = 28.dp, height = 28.dp),
                    colorFilter = ColorFilter.tint(buttonOKColor) )
            }        }
    }
}
@Composable
fun InventoryCardLentes() {
    Card(colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(top = 35.dp)
            .size(width = 312.dp, height = 72.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.lentes),
                        contentDescription = "Imagen de lentes",
                        modifier = Modifier.size(width = 50.dp, height = 50.dp))
                    Text(
                        text = "1%",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 11.dp),
                    )
                }

                Column(
                    modifier = Modifier.padding(start = 5.dp)
                ) {
                    Text(
                        text = "Lentes: 0/3",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    Text(
                        text = "Aumenta el ataque                ",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Image(painter = painterResource(id = R.drawable.plus),
                    contentDescription = "Imagen de cafe",
                    modifier = Modifier.size(width = 28.dp, height = 28.dp),
                    colorFilter = ColorFilter.tint(buttonOKColor) )
            }        }
    }
}
@Composable
fun InventoryCardAtaque() {
    Card(colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .padding(top = 35.dp)
            .size(width = 312.dp, height = 72.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.doble_ataque),
                        contentDescription = "Imagen de cafe",
                        modifier = Modifier.size(width = 50.dp, height = 50.dp))
                    Text(
                        text = "1%",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 11.dp),
                    )
                }

                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = "Doble Ataque: 0/3",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Posibilidad de atacar dos veces",
                        color = buttonOKColor,
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Image(painter = painterResource(id = R.drawable.plus),
                    contentDescription = "Imagen de cafe",
                    modifier = Modifier.size(width = 28.dp, height = 28.dp),
                    colorFilter = ColorFilter.tint(buttonOKColor) )
            }        }
    }
}
@Preview
@Composable
fun PreviewRankingScreen() {
    InventoryScreen()
}