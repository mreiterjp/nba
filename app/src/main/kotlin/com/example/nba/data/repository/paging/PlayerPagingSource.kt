package com.example.nba.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nba.data.api.PlayerApi
import com.example.nba.domain.entity.PlayerEntity
import com.example.nba.domain.entity.toEntity
import retrofit2.HttpException
import java.io.IOException

class PlayerPagingSource(private val playerApi: PlayerApi) : PagingSource<Int, PlayerEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlayerEntity> {
        return try {
            val page = params.key ?: 1
            val response = playerApi.getPlayers(page, params.loadSize).players
            val items: List<PlayerEntity> =
                response.map { player ->
                    player.toEntity()
                }
            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (items.isEmpty()) null else page + 1,
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlayerEntity>): Int? {
        return state.anchorPosition
    }
}
