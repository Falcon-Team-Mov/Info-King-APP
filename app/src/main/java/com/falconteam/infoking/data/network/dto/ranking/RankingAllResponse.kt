package com.falconteam.infoking.data.network.dto.ranking

import com.falconteam.infoking.data.models.Rankings
import com.google.gson.annotations.SerializedName

data class RankingAllResponse(
    @SerializedName("total") var total: Int,
    @SerializedName("rankings") var rankings: List<Rankings>,
    @SerializedName("msg") var msg: String? = null
)
