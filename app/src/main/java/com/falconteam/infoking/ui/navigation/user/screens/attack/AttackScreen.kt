package com.falconteam.infoking.ui.navigation.user.screens.attack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.infoking.R
import com.falconteam.infoking.ui.navigation.user.screens.battle.Background
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.jostBold
import com.falconteam.infoking.ui.theme.jostRegular
import com.falconteam.infoking.ui.theme.jostSemiBold
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.white

@Composable
fun AttackScreen(modifier: Modifier = Modifier,
){
    Background()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
    ) {
        Text(
            text = "CIUDAD {X}",
            color = buttonOKColor,
            fontFamily = jostSemiBold,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(vertical = 65.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.nestor),
            contentDescription = "Img character",
            modifier = Modifier
                .size(width = 144.dp, height = 221.dp)
        )
        LinearProgressIndicator(
            progress = 0.95f,
            color = buttonCancelColor,
            trackColor = buttonOKColor,
            modifier = Modifier
                .padding(top = 35.dp)
                .size(width = 158.dp, height = 5.dp)
                .clip(RoundedCornerShape(10.dp))

        )
            Text(
                text = "95/100 HP",
                fontFamily = jostRegular,
                fontSize = 14.sp,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        AttackCard()
        }

}


@Composable
fun AttackCard(modifier: Modifier = Modifier){
    val opacity = 0.7f
    Card(
        colors = CardDefaults.cardColors(primaryColor.copy(alpha = opacity)),
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 35.dp)
            .size(width = 325.dp, height = 204.dp)
        ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Te has encontrado con un NPC con {%%} HP y de nivel {%%}.\n" +
                        "¿Deseas atacarlo?",
                color = white,
                fontSize = 15.sp,
                fontFamily = jostSemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 5.dp)
                    .padding(horizontal = 45.dp)

            )
            Button(onClick = { /*TODO*/
            },
                colors = ButtonDefaults.buttonColors(secondaryAquaColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 45.dp)
                    .size(width = 221.dp, height = 40.dp)
            ) {
                Text(
                    text = "ATACAR",
                    fontFamily = jostBold,
                    fontSize = 16.sp
                )
            }
            Button(onClick = { /*TODO*/
            },
                colors = ButtonDefaults.buttonColors(secondaryAquaColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 45.dp)
                    .size(width = 221.dp, height = 40.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ray),
                        contentDescription = "",
                        Modifier.size(width = 17.dp, height = 18.dp))
                    Text(
                        text = "PASAR",
                        fontFamily = jostBold,
                        fontSize = 16.sp
                    )
                }
            }

        }

    }

}

@Preview
@Composable
fun PreviewAttackScreen(){
    AttackScreen()
}