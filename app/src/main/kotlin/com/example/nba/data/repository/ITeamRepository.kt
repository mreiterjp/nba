package com.example.nba.data.repository

import com.example.nba.domain.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

interface ITeamRepository {
    suspend fun getTeam(id:Int): Flow<TeamEntity>
}