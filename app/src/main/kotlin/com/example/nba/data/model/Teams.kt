package com.example.nba.data.model

import com.google.gson.annotations.SerializedName

data class Teams(
    @SerializedName("data")
    val teams: List<Team>,
    @SerializedName("meta")
    val pagingInfo: PagingInfo,
)
