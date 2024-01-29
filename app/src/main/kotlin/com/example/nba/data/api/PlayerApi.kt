package com.example.nba.data.api

import com.example.nba.common.Constants.ITEMS_PER_PAGE
import com.example.nba.data.model.Player
import com.example.nba.data.model.Players
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerApi {
    @GET("players")
    suspend fun getPlayers(
        @Query("page") page: Int = 0,
        @Query("per_page") perPage: Int = ITEMS_PER_PAGE,
    ): Players

    @GET("players/{id}")
    suspend fun getPlayer(
        @Path("id") id: Int = 0,
    ): Player
}

