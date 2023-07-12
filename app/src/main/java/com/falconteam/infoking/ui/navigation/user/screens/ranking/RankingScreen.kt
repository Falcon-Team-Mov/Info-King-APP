package com.falconteam.infoking.ui.navigation.user.screens.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.falconteam.infoking.data.models.Rankings
import com.falconteam.infoking.ui.components.PopUpWithIcon
import com.falconteam.infoking.ui.components.TextResponsiveSize
import com.falconteam.infoking.ui.components.formatNumber
import com.falconteam.infoking.ui.components.formatNumberWithComma
import com.falconteam.infoking.ui.navigation.user.screens.tools.LoadingScreen
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.jostSemiBold
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.viewmodels.RankingViewModel

@Composable
fun RankingScreen(
    finish: Boolean
) {
    val viewModel: RankingViewModel = viewModel()
    val context = LocalContext.current
    var _finish = finish
    InfoKingTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                modifier = Modifier.padding(top = 40.dp),
                text = "RANKING GLOBAL",
                color = secondaryAquaColor,
                fontFamily = jostSemiBold,
                fontSize = TextResponsiveSize(size = 40.sp)
            )
            if (!_finish) {
                viewModel.GetAll(context)
                LoadingScreen()
                _finish = !viewModel.finished.value
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(primaryColor)
                        .padding(top = 16.dp)
                ) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.background(primaryColor)
                    ) {
                        itemsIndexed(viewModel.data.entries.toList()) { index, item ->
                            RankingItem(ranking = item.value, position = index + 1)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RankingItem(
    ranking: Rankings,
    position: Int,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    val viewModel: RankingViewModel = viewModel()
    Card(
        colors = CardDefaults.cardColors(secondaryBlueColor),
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 5.dp)
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.18f)
            .clickable(onClick = {
                viewModel.getRanking(ranking.id, context)
                showDialog = true
            })
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            RankingDetail(position, ranking)
        }
        val username = viewModel.dataRankingUser[0]?.username
        val icon = viewModel.dataRankingUser[0]?.icon
        val level = viewModel.dataRankingUser[0]?.nivel
        val attack = viewModel.dataRankingUser[0]?.ataque?.let { formatNumberWithComma(it) }
        val defense = viewModel.dataRankingUser[0]?.defensa?.let { formatNumberWithComma(it) }
        val victories = viewModel.dataRankingUser[0]?.victorias?.let { formatNumberWithComma(it) }
        val loses = viewModel.dataRankingUser[0]?.derrotas?.let { formatNumberWithComma(it) }

        if (showDialog && viewModel.finishedRanking.value) {
            PopUpWithIcon(
                onDismiss = { showDialog = false; viewModel.finishedRanking.value = false },
                onBack = {},
                titleText = username ?: "",
                descriptionText = "Nivel: $level\n" +
                        "Ataque: $attack\n" +
                        "Defensa: $defense\n" +
                        "Victorias: $victories\n" +
                        "Derrotas: $loses",
                buttonText = "CERRAR",
                icon = icon ?: ""
            )
        }
    }
}

@Composable
fun RankingDetail(position: Int, ranking: Rankings, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.2f)
        ) {
            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = position.toString(),
                color = buttonOKColor,
                fontFamily = jostSemiBold,
                fontSize = TextResponsiveSize(size = 28.sp)
            )
        }


        Column(
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = ranking.icon,
                    contentDescription = "Posicion $position",
                    modifier = Modifier
                        .drawBehind {
                            drawRoundRect(
                                Color.Transparent, cornerRadius = CornerRadius(5.dp.toPx())
                            )
                        }
                        .fillMaxHeight(0.3f)
                        .fillMaxWidth(0.3f)
                        .padding(8.dp)
                        .padding(end = 8.dp),
                )
                Text(
                    text = ranking.username,
                    textAlign = TextAlign.Start,
                    color = buttonOKColor,
                    fontFamily = jostSemiBold,
                    fontSize = TextResponsiveSize(size = 24.sp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = formatNumber(ranking.victorias - ranking.derrotas),
                textAlign = TextAlign.End,
                color = buttonOKColor,
                fontFamily = jostSemiBold,
                fontSize = TextResponsiveSize(size = 24.sp)
            )
        }
    }
}

//@Preview
//@Composable
//fun PreviewRankingScreen() {
//    RankingScreen(false)
//}

@Preview
@Composable
fun RankingDetailPreview() {
    RankingDetail(position = 1, ranking = Rankings("", "Francisco.1999999", "", 2340000, 0))
}