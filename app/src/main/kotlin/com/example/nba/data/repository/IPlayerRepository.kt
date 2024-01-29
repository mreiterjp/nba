package com.example.nba.data.repository

import androidx.paging.PagingData
import com.example.nba.domain.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface IPlayerRepository {
    suspend fun getPlayers(): Flow<PagingData<PlayerEntity>>

    suspend fun getPlayer(id: Int): Flow<PlayerEntity>
}
