package com.example.nba.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.nba.common.Constants.ITEMS_PER_PAGE
import com.example.nba.data.api.PlayerApi
import com.example.nba.data.repository.paging.PlayerPagingSource
import com.example.nba.domain.entity.PlayerEntity
import com.example.nba.domain.entity.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A repository class that provides access to player data from the NBA API.
 *
 * @param playerApi The API client used to fetch player data.
 */
class PlayerRepository(private val playerApi: PlayerApi) : IPlayerRepository {
    /**
     * Fetches paginated player data from the API.
     *
     * @return A `Flow` object emitting `PagingData` objects containing the paginated list of `PlayerEntity` objects.
     */
    override suspend fun getPlayers(): Flow<PagingData<PlayerEntity>> {
        return Pager(
            // Configure the Paging library
            config =
                PagingConfig(
                    pageSize = ITEMS_PER_PAGE, // Number of items per page
                    prefetchDistance = 2, // Number of pages to pre-fetch
                ),
            // Create a paging source factory that generates the `PlayerPagingSource`
            pagingSourceFactory = {
                PlayerPagingSource(playerApi)
            },
        ).flow
    }

    /**
     * Retrieves a player by ID.
     *
     * @param id The ID of the player to retrieve.
     * @return A `Flow` object emitting the corresponding `PlayerEntity` object.
     */
    override suspend fun getPlayer(id: Int): Flow<PlayerEntity> {
        return flow {
            // Fetch the player from the API for the specified ID
            val response = playerApi.getPlayer(id)

            // Convert the API response to a `PlayerEntity` object
            val playerEntity = response.toEntity()

            emit(playerEntity)
        }
    }
}
