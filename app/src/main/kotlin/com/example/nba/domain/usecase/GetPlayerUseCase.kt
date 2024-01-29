package com.example.nba.domain.usecase

import com.example.nba.data.repository.PlayerRepository
import com.example.nba.domain.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

class GetPlayerUseCase(private  val playerRepository: PlayerRepository) {
    suspend operator fun invoke(id: Int): Flow<PlayerEntity> {
        return playerRepository.getPlayer(id)
    }
}
