package com.falconteam.infoking.ui.navigation.user.screens.ranking

import android.util.Log
import androidx.compose.foundation.background
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
import com.falconteam.infoking.ui.components.FormatNumber
import com.falconteam.infoking.ui.components.TextResponsiveSize
import com.falconteam.infoking.ui.navigation.user.screens.tools.LoadingScreen
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.jostSemiBold
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.viewmodels.RankingViewModel
import java.text.DecimalFormat

@Composable
fun RankingScreen(
    finish: Boolean
) {
    val viewModel: RankingViewModel = viewModel()
    val context = LocalContext.current
    var _finish = finish
    InfoKingTheme() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(primaryColor)
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            if (!_finish) {
                viewModel.GetAll(context)
                LoadingScreen()
                _finish = !viewModel.finished.value
            } else {
                Text(
                    "RANKING GLOBAL",
                    color = secondaryAquaColor,
                    fontFamily = jostSemiBold,
                    modifier = Modifier.padding(top = 40.dp, bottom = 25.dp),
                    fontSize = TextResponsiveSize(size = 40.sp)
                )

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

@Composable
fun RankingItem(
    ranking: Rankings,
    position: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(secondaryBlueColor),
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 5.dp)
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.18f)

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
    }
}

@Composable
fun RankingDetail(position: Int, ranking: Rankings, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.1f)
        ) {
            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = position.toString(),
                color = buttonOKColor,
                fontFamily = jostSemiBold,
                fontSize = TextResponsiveSize(size = 24.sp)
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
                text = FormatNumber(ranking.victorias - ranking.derrotas),
                textAlign = TextAlign.End,
                color = buttonOKColor,
                fontFamily = jostSemiBold,
                fontSize = TextResponsiveSize(size = 24.sp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewRankingScreen() {
    RankingScreen(false)
}

@Preview
@Composable
fun RankingDetailPreview() {
    RankingDetail(position = 1, ranking = Rankings("", "Francisco.1999999", "", 2340000, 0))
}