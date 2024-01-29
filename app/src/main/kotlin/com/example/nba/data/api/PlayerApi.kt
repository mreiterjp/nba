package com.example.nba.data.api

import com.example.nba.common.Constants.ITEMS_PER_PAGE
import com.example.nba.data.model.Player
import com.example.nba.data.model.Players
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * An interface representing the NBA player API endpoint.
 */
interface PlayerApi {
    /**
     * Retrieves a paginated list of players.
     *
     * @param page The page number (0-based).
     * @param perPage The number of players per page.
     * @return A list of `Players` objects.
     */
    @GET("players")
    suspend fun getPlayers(
        @Query("page") page: Int = 0,
        @Query("per_page") perPage: Int = ITEMS_PER_PAGE,
    ): Players

    /**
     * Retrieves a specific player by ID.
     *
     * @param id The ID of the player to retrieve.
     * @return A `Player` object.
     */
    @GET("players/{id}")
    suspend fun getPlayer(
        @Path("id") id: Int = 0,
    ): Player
}
