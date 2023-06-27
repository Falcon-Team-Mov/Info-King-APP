package com.falconteam.infoking.ui.navigation.user.screens.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.falconteam.infoking.data.models.Ranking
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor

@Composable
fun RankingScreen() {
    val viewModel: RankingViewModel = viewModel()
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(primaryColor)
    ) {
        item {
            Text(
                "RANKING GLOBAL",
                modifier = Modifier.padding(vertical = 25.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = secondaryAquaColor,
            )
        }
        items(viewModel.state.value) { ranking ->
            RankingItem(ranking = ranking)
        }
    }
}

@Composable
fun RankingItem(
    ranking: Ranking,
    modifier: Modifier = Modifier,
) {
    Card(colors = CardDefaults.cardColors(secondaryBlueColor),
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 5.dp)
            .size(width = 359.dp, height = 45.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            RankingDetail(ranking)
        }
    }
}

@Composable
fun RankingDetail(ranking: Ranking, modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = ranking.position,
            color = buttonOKColor,
            style = MaterialTheme.typography.bodySmall

        )

        Icon(
            imageVector = Icons.Default.PlayCircleFilled,
            contentDescription = "Icono de jugador",
            Modifier.drawBehind { drawRoundRect(
                primaryColor, cornerRadius = CornerRadius(5.dp.toPx())
            ) }
                .padding(5.dp),
            tint = secondaryAquaColor
        )

        Text(
            text = ranking.name,
            color = buttonOKColor,
            style = MaterialTheme.typography.bodySmall

        )
        Text(
            text = ranking.type,
            color = buttonOKColor,
            style = MaterialTheme.typography.bodySmall            )

    }
}

@Preview
@Composable
fun PreviewRankingScreen() {
    RankingScreen()
}