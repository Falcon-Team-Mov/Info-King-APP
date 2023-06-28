package com.falconteam.infoking.ui.navigation.user.screens.battle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.infoking.R
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.jostBold
import com.falconteam.infoking.ui.theme.jostRegular
import com.falconteam.infoking.ui.theme.jostSemiBold
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.theme.white

@Composable
fun BattleBackground(modifier: Modifier = Modifier){
    val opacity = 0.2f
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(primaryColor)
            .graphicsLayer(alpha = opacity)
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.texture),
                contentScale = ContentScale.FillBounds
            )
    ) {}

}

@Composable
fun BattleScreen(modifier: Modifier = Modifier){
    BattleBackground()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
            Text(
                text = "TE ENCUENTRAS EN:",
                style = MaterialTheme.typography.bodyLarge,
                color = white,
                fontSize = 16.sp
            )
        BattleCard()
    }

}

@Composable
fun BattleCard(modifier: Modifier = Modifier){
    Card(
        colors = CardDefaults.cardColors(secondaryBlueColor),
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 35.dp)
            .size(width = 312.dp, height = 250.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "HOLA, MUNDO",
                color = white,
                fontSize = 22.sp,
                fontFamily = jostSemiBold,
                modifier = Modifier.padding(top = 45.dp)
            )
            Text(
                text = "Donde todo comenzó...",
                color = white,
                fontSize = 15.sp,
                fontFamily = jostRegular,
                modifier = Modifier.padding(top = 25.dp)

            )
            Button(onClick = { /*TODO*/
            },
                colors = ButtonDefaults.buttonColors(buttonCancelColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp, horizontal = 35.dp)
            ) {
                Text(
                    text = "BUSCAR ENEMIGO",
                    fontFamily = jostBold,
                    fontSize = 15.sp
                )
            }
        }

    }

}

@Preview
@Composable
fun PreviewBattleScreen(){
    BattleScreen()
}