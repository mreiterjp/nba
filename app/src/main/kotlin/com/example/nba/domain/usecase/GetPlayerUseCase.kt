package com.example.nba.domain.usecase

import com.example.nba.data.repository.PlayerRepository
import com.example.nba.domain.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

/**
 * A use case that retrieves a specific player by ID.
 *
 * @param playerRepository The repository used to fetch player data from the API.
 */
class GetPlayerUseCase(private val playerRepository: PlayerRepository) {
    /**
     * Retrieves a specific player by ID and emits it as a `Flow` of `PlayerEntity` objects.
     *
     * @param id The ID of the player to retrieve.
     * @return A `Flow` of `PlayerEntity` objects containing the player data.
     */
    suspend operator fun invoke(id: Int): Flow<PlayerEntity> {
        return playerRepository.getPlayer(id)
    }
}
