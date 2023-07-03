package com.falconteam.infoking.ui.navigation.user.screens.fight

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
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
import com.falconteam.infoking.ui.components.Background
import com.falconteam.infoking.ui.navigation.user.screens.attack.AttackCard
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.jostBold
import com.falconteam.infoking.ui.theme.jostRegular
import com.falconteam.infoking.ui.theme.jostSemiBold
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.theme.white
import java.time.format.TextStyle

@Composable
fun FightScreen( modifier: Modifier = Modifier ) {
    Background()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "CIUDAD X",
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
                .size(width = 312.dp, height = 450.dp)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                ) {
                    FightItemCharacter()
                    FightItemEnemy()
                }

                LinearProgressIndicator(
                    progress = 0.05f,
                    color = buttonCancelColor,
                    trackColor = buttonOKColor,
                    modifier = Modifier
                        .size(width = 250.dp, height = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
        Button(onClick = { /*TODO*/
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
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun FightItemCharacter(modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(12.dp)
            .padding(bottom = 25.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.character),
            contentDescription = "Img character",
            modifier = Modifier
                .size(width = 94.dp, height = 150.dp) )
        Text(
            text = "ENEMIGO X",
            color = white,
            fontFamily = jostSemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top =12.dp)

        )
        LinearProgressIndicator(
            progress = 0.25f,
            color = buttonCancelColor,
            trackColor = buttonOKColor,
            modifier = Modifier
                .padding(top = 15.dp)
                .size(width = 94.dp, height = 4.dp)
                .clip(RoundedCornerShape(10.dp))

        )
        Fightdetail()
    }

}

@Composable
fun FightItemEnemy(modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(12.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.nestor),
            contentDescription = "Img character",
            modifier = Modifier
                .size(width = 94.dp, height = 150.dp)
        )
        Text(
            text = "Marroquin",
            color = white,
            fontFamily = jostSemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top =12.dp)
        )
        LinearProgressIndicator(
            progress = 0.25f,
            color = buttonCancelColor,
            trackColor = buttonOKColor,
            modifier = Modifier
                .padding(top = 15.dp)
                .size(width = 94.dp, height = 4.dp)
                .clip(RoundedCornerShape(10.dp))

        )
        Fightdetail2()
    }

}

@Composable
fun Fightdetail(modifier: Modifier = Modifier){
    Row() {
        Column(
            modifier = Modifier
                .padding(5.dp)
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
            modifier = Modifier
                .padding(5.dp)
        ) {
            Text(
                text = "xxx",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "xxx",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "xxx",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
    }
}
@Composable
fun Fightdetail2(modifier: Modifier = Modifier){
    Row() {
        Column(
            modifier = Modifier
                .padding(5.dp)
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
            modifier = Modifier
                .padding(5.dp)
        ) {
            Text(
                text = "xxx",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "xxx",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "xxx",
                fontFamily = jostRegular,
                color = white,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
    }
}
@Preview
@Composable
fun PreviewFightScreen(){
    FightScreen()
}