package com.example.nba.data.api

import com.example.nba.common.Constants.ITEMS_PER_PAGE
import com.example.nba.data.model.Team
import com.example.nba.data.model.Teams
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamApi {
    @GET("teams")
    suspend fun getTeams(
        @Query("page") page: Int = 0,
        @Query("per_page") perPage: Int = ITEMS_PER_PAGE,
    ): Teams

    @GET("teams/{id}")
    suspend fun getTeam(
        @Path("id") id: Int = 0,
    ): Team
}
