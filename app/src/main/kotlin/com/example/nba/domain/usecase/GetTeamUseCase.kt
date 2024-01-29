package com.example.nba.domain.usecase

import com.example.nba.data.repository.TeamRepository
import com.example.nba.domain.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

class GetTeamUseCase(private val teamRepository:TeamRepository) {
    suspend operator fun invoke(id:Int): Flow<TeamEntity> {
        return teamRepository.getTeam(id)
    }
}