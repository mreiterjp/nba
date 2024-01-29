package com.example.nba.data.repository

import com.example.nba.data.api.TeamApi
import com.example.nba.domain.entity.TeamEntity
import com.example.nba.domain.entity.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TeamRepository(
    private val teamApi: TeamApi
) : ITeamRepository {
    override suspend fun getTeam(id: Int): Flow<TeamEntity> {
        return flow {
            emit(
                teamApi.getTeam(id).toEntity()
            )
        }
    }
}
