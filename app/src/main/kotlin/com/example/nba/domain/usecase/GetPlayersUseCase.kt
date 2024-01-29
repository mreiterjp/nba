package com.example.nba.domain.usecase

import androidx.paging.PagingData
import com.example.nba.data.repository.PlayerRepository
import com.example.nba.domain.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

/**
 * A use case that fetches paginated player data from the NBA API.
 *
 * @param playerRepository The repository used to fetch player data from the API.
 */
class GetPlayersUseCase(private val playerRepository: PlayerRepository) {
    /**
     * Fetches paginated player data from the API and emits it as a `Flow` of `PagingData` objects.
     *
     * @return A `Flow` of `PagingData` objects containing the paginated list of `PlayerEntity` objects.
     */
    suspend operator fun invoke(): Flow<PagingData<PlayerEntity>> {
        return playerRepository.getPlayers()
    }
}
