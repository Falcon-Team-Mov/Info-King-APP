package com.falconteam.infoking.data.network.repository

import com.falconteam.infoking.data.models.SessionStatsData
import com.falconteam.infoking.data.models.SessionUserData
import com.falconteam.infoking.data.models.StatsUpdate
import com.falconteam.infoking.data.network.dto.npc.NpcAllResponse
import com.falconteam.infoking.data.network.dto.ranking.RankingRequest
import com.falconteam.infoking.data.network.service.NpcService
import retrofit2.HttpException
import java.io.IOException

class NpcRepository(private val api: NpcService) {
    suspend fun NpcAll(id: String): NpcAllResponse {
        try {
            val response = api.getNpcAll(id)
            return response
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return NpcAllResponse(
                    total = 0,
                    npcs = emptyList(),
                    msg = "E-EE1"
                )
            }
            return NpcAllResponse(
                total = 0,
                npcs = emptyList(),
                msg = "E-EE2"
            )
        } catch (e: IOException) {
            return NpcAllResponse(
                total = 0,
                npcs = emptyList(),
                msg = "E-EE3"
            )
        }
    }

    suspend fun putUser(id: String, data: SessionUserData): Any? {
        try {
            val response = api.putUser(id, data)
            return response
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return "E-EE1"
            }
            return "E-EE2"
        } catch (e: IOException) {
            return "E-EE3"
        }
    }

    suspend fun putStats(id: String, data: StatsUpdate): Any? {
        try {
            val response = api.putStats(id, data)
            return response
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return "E-EE1"
            }
            return "E-EE2"
        } catch (e: IOException) {
            return "E-EE3"
        }
    }

    suspend fun putVictoryRanking(data: RankingRequest): Any? {
        try {
            val response = api.putVictoryRanking(data)
            return response
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return "E-EE1"
            }
            return "E-EE2"
        } catch (e: IOException) {
            return "E-EE3"
        }
    }

    suspend fun putDerrotRanking(data: RankingRequest): Any? {
        try {
            val response = api.putDerrotRanking(data)
            return response
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return "E-EE1"
            }
            return "E-EE2"
        } catch (e: IOException) {
            return "E-EE3"
        }
    }
}