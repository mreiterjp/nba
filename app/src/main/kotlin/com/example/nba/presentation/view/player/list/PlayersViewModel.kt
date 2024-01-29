package com.example.nba.presentation.view.player.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nba.domain.entity.PlayerEntity
import com.example.nba.domain.usecase.GetPlayersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing the state of the list of players.
 *
 * @param getPlayersUseCase The use case for retrieving a list of players.
 */
class PlayersViewModel(
    private val getPlayersUseCase: GetPlayersUseCase,
) : ViewModel() {
    /**
     * Internal state flow that holds the latest list of players.
     */
    private val playersStateInternal = MutableStateFlow<PagingData<PlayerEntity>>(PagingData.empty())

    /**
     * Observable state flow that provides the latest list of players.
     */
    public val playersState: MutableStateFlow<PagingData<PlayerEntity>> get() = playersStateInternal

    /**
     * Initializes the view model by fetching the list of players.
     */
    init {
        onEvent(PlayersEvent.Init)
    }

    /**
     * Handles incoming events.
     *
     * @param event The event to handle.
     */
    private fun onEvent(event: PlayersEvent) {
        viewModelScope.launch {
            when (event) {
                is PlayersEvent.Init -> {
                    getPlayers()
                }
            }
        }
    }

    /**
     * Fetches the list of players from the backend.
     */
    private suspend fun getPlayers() {
        getPlayersUseCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                playersStateInternal.value = it
            }
    }
}

/**
 * Sealed class for player-related events.
 */
sealed class PlayersEvent {
    /**
     * Event indicating the initial load of players.
     */
    object Init : PlayersEvent()
}
