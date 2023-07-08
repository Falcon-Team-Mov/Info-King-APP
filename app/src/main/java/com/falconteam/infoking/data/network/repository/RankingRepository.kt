package com.falconteam.infoking.data.network.repository

import android.util.Log
import com.falconteam.infoking.data.models.ProfileRankingData
import com.falconteam.infoking.data.models.RankingUserInfo
import com.falconteam.infoking.data.models.Rankings
import com.falconteam.infoking.data.network.dto.ranking.RankingAllResponse
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

    suspend fun getPositionRanking(id: String): ProfileRankingData {
        try {
            return api.getPositionRanking(id)
        } catch (e: HttpException) {
            if (e.code() === 400) {
                return ProfileRankingData(
                    position = -1,
                    0,
                    0,
                    0,
                    msg = "E-RO"
                )
            }
            return ProfileRankingData(
                position = -1,
                0,
                0,
                0,
                msg = "E-RO-D-1"
            )
        } catch (e: IOException) {
            return ProfileRankingData(
                position = -1,
                0,
                0,
                0,
                msg = "E-RO-D-2"
            )
        }
    }

    suspend fun getRanking(id: String): RankingUserInfo {
        try {
            return api.getRanking(id)
        } catch (e: HttpException) {
            return RankingUserInfo(
                "",
                "",
                "",
                -1,
                -1,
                -1,
                -1,
                -1
            )
        } catch (e: IOException) {
            return RankingUserInfo(
                "",
                "",
                "",
                -1,
                -1,
                -1,
                -1,
                -1
            )
        }
    }

}