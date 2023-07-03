package com.falconteam.infoking.data.network.service

import com.falconteam.infoking.data.models.SessionStatsData
import com.falconteam.infoking.data.models.SessionUserData
import com.falconteam.infoking.data.models.StatsUpdate
import com.falconteam.infoking.data.models.User
import com.falconteam.infoking.data.network.dto.npc.NpcAllResponse
import com.falconteam.infoking.data.network.dto.ranking.RankingRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface NpcService {
    @GET("api/npc/{id}")
    suspend fun getNpcAll(@Path("id") id: String): NpcAllResponse

    @PUT("api/users/{id}")
    suspend fun putUser(@Path("id") id: String, @Body data: SessionUserData): Any?

    @PUT("api/stats/{id}")
    suspend fun putStats(@Path("id") id: String, @Body data: StatsUpdate): Any?

    @PUT("api/ranking/victory")
    suspend fun putVictoryRanking(@Body data: RankingRequest): Any?

    @PUT("api/ranking/derrot")
    suspend fun putDerrotRanking(@Body data: RankingRequest): Any?
}