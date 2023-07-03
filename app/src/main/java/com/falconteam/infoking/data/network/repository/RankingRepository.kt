package com.falconteam.infoking.data.network.repository

import com.falconteam.infoking.data.models.Rankings
import com.falconteam.infoking.data.network.dto.ranking.RankingAllResponse
import com.falconteam.infoking.data.network.dto.ranking.RankingRequest
import com.falconteam.infoking.data.network.dto.ranking.RankingResponse
import com.falconteam.infoking.data.network.service.RankingService
import retrofit2.HttpException
import java.io.IOException

class RankingRepository(private val api: RankingService) {
    suspend fun getRankingAll(): RankingAllResponse {
        try {
            return api.getRankingAll()
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return RankingAllResponse(
                    total = 0,
                    rankings = listOf<Rankings>(),
                    msg = "E-RO"
                )
            }
            return RankingAllResponse(
                total = 0,
                rankings = listOf<Rankings>(),
                msg = "E-RO-D-1"
            )
        } catch (e: IOException) {
            return RankingAllResponse(
                total = 0,
                rankings = listOf<Rankings>(),
                msg = "E-RO-D-2"
            )
        }
    }

    suspend fun getRanking(id: String): RankingResponse {
        try {
            return api.getRanking(id)
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return RankingResponse(
                    ranking = Rankings(
                        "",
                        "",
                        "",
                        -1,
                        -1,
                    ),
                    msg = "E-RO"
                )
            }
            return RankingResponse(
                ranking = Rankings(
                    "",
                    "",
                    "",
                    -1,
                    -1,
                ),
                msg = "E-RO-D-1"
            )
        } catch (e: IOException) {
            return RankingResponse(
                ranking = Rankings(
                    "",
                    "",
                    "",
                    -1,
                    -1,
                ),
                msg = "E-RO-D-2"
            )
        }
    }

}