package com.example.nba.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nba.data.api.PlayerApi
import com.example.nba.domain.entity.PlayerEntity
import com.example.nba.domain.entity.toEntity
import retrofit2.HttpException
import java.io.IOException

/**
 * A PagingSource implementation that fetches paginated data for players from the NBA API.
 *
 * @param playerApi The API client used to fetch player data.
 */
class PlayerPagingSource(private val playerApi: PlayerApi) : PagingSource<Int, PlayerEntity>() {
    /**
     * Loads the next page of players from the API.
     *
     * @param params The load parameters specifying the requested page and load size.
     * @return A `LoadResult` object indicating the success or failure of the load and the loaded data.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlayerEntity> {
        return try {
            // Fetch the players from the API for the specified page
            val page = params.key ?: 1 // Default to page 1 if no key is provided
            val response = playerApi.getPlayers(page, params.loadSize).players

            // Convert the API response to `PlayerEntity` objects
            val items =
                response.map { player ->
                    player.toEntity()
                }

            // Create a `LoadResult` object containing the loaded data and pagination keys
            val hasNextPage = items.isNotEmpty()
            val nextKey = if (hasNextPage) page + 1 else null
            val prevKey = if (page == 1) null else page - 1
            return LoadResult.Page(
                data = items,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (exception: IOException) {
            // Handle network or IO errors
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            // Handle API errors
            LoadResult.Error(exception)
        }
    }

    /**
     * Returns the refresh key for the current PagingState.
     *
     * @param state The current PagingState.
     * @return The refresh key or `null` if the state is invalid.
     */
    override fun getRefreshKey(state: PagingState<Int, PlayerEntity>): Int? {
        // Return the anchor position if the state is valid
        return state.anchorPosition
    }
}
