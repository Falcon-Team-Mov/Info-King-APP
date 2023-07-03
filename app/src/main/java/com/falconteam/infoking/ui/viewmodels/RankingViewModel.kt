package com.falconteam.infoking.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.infoking.RetrofitApplication
import com.falconteam.infoking.data.models.Rankings
import kotlinx.coroutines.launch

class RankingViewModel : ViewModel() {
    val data = mutableStateMapOf<Int, Rankings>()
    val finished = mutableStateOf<Boolean>(false)
    val repository_ranking = RetrofitApplication()._rankingRepository

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
}