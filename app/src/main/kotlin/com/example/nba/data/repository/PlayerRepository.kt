package com.example.nba.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.nba.common.Constants.ITEMS_PER_PAGE
import com.example.nba.data.api.PlayerApi
import com.example.nba.data.repository.paging.PlayerPagingSource
import com.example.nba.domain.entity.PlayerEntity
import com.example.nba.domain.entity.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * The PlayerRepository class is be responsible for retrieving paginated NBA player data from the data layer.
 */
class PlayerRepository(private val playerApi: PlayerApi) : IPlayerRepository {

    override suspend fun getPlayers(): Flow<PagingData<PlayerEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
                prefetchDistance = 2
            ),
            pagingSourceFactory = {
                PlayerPagingSource(playerApi)
            }
        ).flow
    }

    override suspend fun getPlayer(id: Int): Flow<PlayerEntity> {
        return flow {
            emit(
                playerApi.getPlayer(id).toEntity()
            )
        }
    }
}
