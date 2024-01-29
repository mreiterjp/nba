package com.example.nba.presentation.view.player.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nba.domain.entity.PlayerEntity
import com.example.nba.domain.usecase.GetPlayerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(
    private val getPlayerUseCase: GetPlayerUseCase,
    private val id: Int,
) : ViewModel() {
    private val _playerState: MutableStateFlow<PlayerEntity> =
        MutableStateFlow(value = PlayerEntity.generateFakePlayerEntity(-1))
    val playerState: MutableStateFlow<PlayerEntity> get() = _playerState

    init {
        onEvent(PlayerEvent.Init)
    }

    private fun onEvent(event: PlayerEvent) {
        viewModelScope.launch {
            when (event) {
                is PlayerEvent.Init -> {
                    getPlayer(id)
                }
            }
        }
    }

    private suspend fun getPlayer(id: Int) {
        getPlayerUseCase(id)
            .collect {
                _playerState.value = it
            }
    }
}

sealed class PlayerEvent {
    object Init : PlayerEvent()
}
