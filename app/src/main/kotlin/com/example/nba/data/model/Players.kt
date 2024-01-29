package com.example.nba.data.model

import com.google.gson.annotations.SerializedName

data class Players(
    @SerializedName("data")
    val players: List<Player>,
    @SerializedName("meta")
    val pagingInfo: PagingInfo,
)
