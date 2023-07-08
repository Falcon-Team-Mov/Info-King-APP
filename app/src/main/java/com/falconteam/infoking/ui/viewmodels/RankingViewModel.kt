package com.falconteam.infoking.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.infoking.RetrofitApplication
import com.falconteam.infoking.data.models.ProfileRankingData
import com.falconteam.infoking.data.models.RankingUserInfo
import com.falconteam.infoking.data.models.Rankings
import com.falconteam.infoking.ui.components.PreferencesKeys.ID
import com.falconteam.infoking.ui.components.getData
import com.falconteam.infoking.ui.components.setFullDataRanking
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RankingViewModel : ViewModel() {
    val data = mutableStateMapOf<Int, Rankings>()
    val dataRankingProfile = mutableStateMapOf<Int, ProfileRankingData>()
    val finished = mutableStateOf<Boolean>(false)
    val finished_profile = mutableStateOf(false)
    val repository_ranking = RetrofitApplication()._rankingRepository
    val dataRankingUser = mutableStateMapOf<Int, RankingUserInfo>()
    var finishedRanking = mutableStateOf(false)

    fun GetAll(context: Context) {
        viewModelScope.launch {
            val value = repository_ranking.getRankingAll()
            val total = value.total

            if (total > 0 && value.msg == null) {
                val ranking = value.rankings

                ranking.forEachIndexed { index, item ->
                    data[index] = item
                }
                finished.value = true
            } else {
                data[0] = Rankings(
                    "", "Ningun usuario en el ranking", "", 0, 0
                )
                finished.value = true
            }
        }
    }

    fun getPosition(context: Context) {
        viewModelScope.launch {
            val value = repository_ranking.getPositionRanking(
                runBlocking {
                    getData(context, ID).toString()
                }
            )
            dataRankingProfile[0] = value
            finished_profile.value = true
        }
    }

    fun getRanking(id: String, context: Context) {
        viewModelScope.launch {
            val value = repository_ranking.getRanking(id)
            setFullDataRanking(context, value)
            dataRankingUser[0] = value
            finishedRanking.value = true
        }
    }
}