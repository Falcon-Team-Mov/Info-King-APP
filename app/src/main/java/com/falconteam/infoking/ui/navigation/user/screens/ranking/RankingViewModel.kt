package com.falconteam.infoking.ui.navigation.user.screens.ranking

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.falconteam.infoking.data.models.Ranking
import com.falconteam.infoking.data.models.ranking

class RankingViewModel: ViewModel() {

    val state = mutableStateOf<List<Ranking>>(getRanking())

    private fun getRanking(): List<Ranking> = ranking

}