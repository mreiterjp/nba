package com.example.nba.data.model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("position")
    val position: String?,
    @SerializedName("height_feet")
    val heightFeet: Int?,
    @SerializedName("height_inches")
    val heightInches: Int?,
    @SerializedName("weight_pounds")
    val weightPounds: Int?,
    @SerializedName("team")
    val team: Team?,
)
