package com.example.nba.domain.usecase

import androidx.paging.PagingData
import com.example.nba.data.repository.PlayerRepository
import com.example.nba.domain.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

class GetPlayersUseCase(private val playerRepository: PlayerRepository) {
    suspend operator fun invoke(): Flow<PagingData<PlayerEntity>> {
        return playerRepository.getPlayers()
    }
}
