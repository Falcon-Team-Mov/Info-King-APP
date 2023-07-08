package com.falconteam.infoking.ui.viewmodels

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.infoking.RetrofitApplication
import com.falconteam.infoking.data.models.SessionUserData
import com.falconteam.infoking.data.models.StatsUpdate
import com.falconteam.infoking.data.models.npc
import com.falconteam.infoking.data.network.dto.ranking.RankingRequest
import com.falconteam.infoking.ui.components.generateRandomNumber
import kotlinx.coroutines.launch

class AttackViewModel() : ViewModel() {
    val data = mutableStateMapOf<Int, npc>()
    val errors = mutableStateOf(false)
    val finished = mutableStateOf(false)

    val repository_Attack = RetrofitApplication()._npcRepository

    fun getNPCAll(id: String) {
        viewModelScope.launch {
            finished.value = false
            val value = repository_Attack.NpcAll(id)
            when (value.msg) {
                null -> {

                    if (value.total > 0) {
                        val id = generateRandomNumber(value.total-1)
                        val npcs = value.npcs[id]
                        data[0] = npcs!!
                        finished.value = true
                    } else {
                        errors.value = true
                    }
                }

                else -> {
                    errors.value = true
                }
            }
        }
    }

    fun putUser(id: String, data: SessionUserData) {
        viewModelScope.launch {
            finished.value = false
            repository_Attack.putUser(id, data)

        }
    }

    fun putStats(id: String, data: StatsUpdate) {
        viewModelScope.launch {
            finished.value = false
            repository_Attack.putStats(id, data)

        }
    }

    fun putVictoryRanking(data: RankingRequest) {
        viewModelScope.launch {
            finished.value = false
            repository_Attack.putVictoryRanking(data)

        }
    }

    fun putDerrotRanking(data: RankingRequest) {
        viewModelScope.launch {
            finished.value = false
           repository_Attack.putDerrotRanking(data)
        }
    }
}