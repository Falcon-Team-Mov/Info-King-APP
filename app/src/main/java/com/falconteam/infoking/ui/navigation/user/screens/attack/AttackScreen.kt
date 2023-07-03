package com.falconteam.infoking.ui.navigation.user.screens.attack

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.infoking.R
import com.falconteam.infoking.ui.components.Background
import com.falconteam.infoking.ui.components.TextResponsiveSize
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.jostBold
import com.falconteam.infoking.ui.theme.jostRegular
import com.falconteam.infoking.ui.theme.jostSemiBold
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.white

@Composable
fun AttackScreen(
    modifier: Modifier = Modifier,
) {
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
                .fillMaxHeight(0.5f)
        ) {
            Text(
                text = "CIUDAD {X}",
                color = buttonOKColor,
                fontFamily = jostSemiBold,
                fontSize = TextResponsiveSize(32.sp),
                modifier = Modifier
                    .padding(vertical = 65.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.nestor),
                contentDescription = "Img character",
                modifier = Modifier
                    .fillMaxHeight(0.7f)
            )
            LinearProgressIndicator(
                progress = 0.95f,
                color = buttonCancelColor,
                trackColor = buttonOKColor,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .fillMaxWidth(0.6f)
                    .clip(RoundedCornerShape(10.dp))

            )
            Text(
                text = "95/100 HP",
                fontFamily = jostRegular,
                fontSize = TextResponsiveSize(16.sp),
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
        AttackCard()
    }

}


@Composable
fun AttackCard(modifier: Modifier = Modifier) {
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
                text = "Te has encontrado con un NPC con {%%} HP y de nivel {%%}.\n" +
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
                onClick = { /*TODO*/
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
    AttackScreen()
}