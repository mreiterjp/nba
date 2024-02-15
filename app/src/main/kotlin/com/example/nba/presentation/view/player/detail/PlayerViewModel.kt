package com.example.nba.presentation.view.player.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nba.domain.entity.PlayerEntity
import com.example.nba.domain.usecase.GetPlayerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * A view model that manages the data for a player.
 *
 * This class provides a way to fetch and observe player data, and it also exposes a state flow that represents the player's most recent information.
 */
class PlayerViewModel(
    private val getPlayerUseCase: GetPlayerUseCase,
    private val id: Int,
) : ViewModel() {
    /**
     * A private mutable state flow that stores the player's most recent information.
     */
    private val playerStateInternal = MutableStateFlow<PlayerEntity?>(null)

    /**
     * An observable state flow that is externally exposed to represent the player's most recent information.
     */
    val playerState: MutableStateFlow<PlayerEntity?> get() = playerStateInternal

    /**
     * Initializes the view model by fetching the player's data from the API.
     */
    init {
        onEvent(PlayerEvent.Init)
    }

    /**
     * Handles a player event.
     *
     * @param event The player event to handle.
     */
    private fun onEvent(event: PlayerEvent) {
        viewModelScope.launch {
            when (event) {
                is PlayerEvent.Init -> {
                    getPlayer(id)
                }
            }
        }
    }

    /**
     * Fetches the player's data from the API and updates the player's state flow accordingly.
     *
     * @param id The ID of the player to fetch data for.
     */
    private suspend fun getPlayer(id: Int) {
        getPlayerUseCase(id)
            .collect {
                it?.let {
                    playerStateInternal.value = it
                }
            }
    }
}

/**
 * A sealed class that represents player events.
 *
 * This class is used to represent events that can occur in the `PlayerViewModel` class.
 */
sealed class PlayerEvent {
    // Indicates that the view model should fetch the player's data from the API.
    object Init : PlayerEvent()
}
