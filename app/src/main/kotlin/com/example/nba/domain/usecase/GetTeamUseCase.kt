package com.example.nba.domain.usecase

import com.example.nba.data.repository.TeamRepository
import com.example.nba.domain.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

/**
 * A use case that retrieves a specific team by ID.
 *
 * @param teamRepository The repository used to fetch team data from the API.
 */
class GetTeamUseCase(private val teamRepository: TeamRepository) {
    /**
     * Retrieves a specific team by ID and emits it as a `Flow` of `TeamEntity` objects.
     *
     * @param id The ID of the team to retrieve.
     * @return A `Flow` of `TeamEntity` objects containing the team data.
     */
    suspend operator fun invoke(id: Int): Flow<TeamEntity?> {
        return teamRepository.getTeam(id)
    }
}
