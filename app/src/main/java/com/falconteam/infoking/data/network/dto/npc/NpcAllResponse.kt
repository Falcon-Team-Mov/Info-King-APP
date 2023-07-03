package com.falconteam.infoking.data.network.dto.npc

import com.falconteam.infoking.data.models.npc
import com.google.gson.annotations.SerializedName

data class NpcAllResponse(
    @SerializedName("total") var total: Int,
    @SerializedName("_npcs") var npcs: List<npc>,
    @SerializedName("msg") var msg: String? = null
)
