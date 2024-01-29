package com.example.nba.data.api

import com.example.nba.common.Constants.ITEMS_PER_PAGE
import com.example.nba.data.model.Team
import com.example.nba.data.model.Teams
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * An interface representing the NBA team API endpoint.
 */
interface TeamApi {
    /**
     * Retrieves a paginated list of teams.
     *
     * @param page The page number (0-based).
     * @param perPage The number of teams per page.
     * @return A list of `Teams` objects.
     */
    @GET("teams")
    suspend fun getTeams(
        @Query("page") page: Int = 0,
        @Query("per_page") perPage: Int = ITEMS_PER_PAGE,
    ): Teams

    /**
     * Retrieves a specific team by ID.
     *
     * @param id The ID of the team to retrieve.
     * @return A `Team` object.
     */
    @GET("teams/{id}")
    suspend fun getTeam(
        @Path("id") id: Int = 0,
    ): Team
}
