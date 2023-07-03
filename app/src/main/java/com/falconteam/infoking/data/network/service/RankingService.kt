package com.falconteam.infoking.data.network.service

import com.falconteam.infoking.data.network.dto.ranking.RankingAllResponse
import com.falconteam.infoking.data.network.dto.ranking.RankingRequest
import com.falconteam.infoking.data.network.dto.ranking.RankingResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RankingService {
    @GET("api/ranking")
    suspend fun getRankingAll(): RankingAllResponse

    @GET("api/ranking/{id}")
    suspend fun getRanking(@Path("id") id: String): RankingResponse

    @POST("api/ranking")
    suspend fun postRanking(@Body data: RankingRequest): RankingResponse


}