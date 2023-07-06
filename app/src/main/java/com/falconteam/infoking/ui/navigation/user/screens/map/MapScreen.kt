package com.falconteam.infoking.ui.navigation.user.screens.map

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.falconteam.infoking.ui.theme.mapColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor

@Composable
fun MapScreen() {
    InfoKingTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(mapColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp),
                        text = "MAPA",
                        textAlign = TextAlign.Center,
                        color = buttonOKColor,
                        fontSize = TextResponsiveSize(size = 40.sp)
                    )

                    Icon(
                        modifier = Modifier
                            .padding(end = 32.dp)
                            .size(ElementResponsiveSize(size = 40.dp)),
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info Button",
                        tint = buttonOKColor
                    )
                }

                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 100.dp),
                    painter = painterResource(R.drawable.mapa),
                    contentDescription = "Map",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Preview
@Composable
fun MapScreenPreview() {
    MapScreen()
}