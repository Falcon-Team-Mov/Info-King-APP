package com.falconteam.infoking.ui.navigation.user.screens.battle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.falconteam.infoking.ui.components.Background
import com.falconteam.infoking.ui.components.TextResponsiveSize
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.jostBold
import com.falconteam.infoking.ui.theme.jostRegular
import com.falconteam.infoking.ui.theme.jostSemiBold
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.theme.white

@Composable
fun BattleScreen(
    onClick: () -> Unit
) {
    Background()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "TE ENCUENTRAS EN:",
            style = MaterialTheme.typography.bodyLarge,
            color = white,
            fontSize = TextResponsiveSize(size = 22.sp)
        )
        BattleCard(onClick = onClick)
    }

}

@Composable
fun BattleCard(onClick: () -> Unit) {
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
                onClick = { onClick() },
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
            }
        }

    }

}

@Preview
@Composable
fun PreviewBattleScreen() {
    BattleScreen(
        onClick = {}
    )
}