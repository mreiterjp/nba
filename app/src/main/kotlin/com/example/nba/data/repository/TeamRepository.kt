package com.example.nba.data.repository

import com.example.nba.data.api.TeamApi
import com.example.nba.domain.entity.TeamEntity
import com.example.nba.domain.entity.toEntity
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A repository class that provides access to team data from the NBA API.
 *
 * @param teamApi The API client used to fetch team data.
 */
class TeamRepository(
    private val teamApi: TeamApi,
) : ITeamRepository {
    /**
     * Retrieves a team by ID.
     *
     * @param id The ID of the team to retrieve.
     * @return A `Flow` object emitting the corresponding `TeamEntity` object.
     */
    override suspend fun getTeam(id: Int): Flow<TeamEntity?> {
        return flow {
            // Fetch the team from the API for the specified ID
            val response = teamApi.getTeam(id)
            var teamEntity: TeamEntity? = null
            // Convert the API response to a `TeamEntity` object
            response.onSuccess {
                teamEntity = data.toEntity()
            }
            emit(teamEntity)
        }
    }
}
