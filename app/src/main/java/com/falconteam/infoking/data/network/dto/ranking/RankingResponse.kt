package com.falconteam.infoking.data.network.dto.ranking

import com.falconteam.infoking.data.models.Rankings

data class RankingResponse(
    val ranking: Rankings,
    val msg: String? = null
)
