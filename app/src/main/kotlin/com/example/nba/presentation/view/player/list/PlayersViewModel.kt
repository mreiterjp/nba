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

class PlayersViewModel(
    private val getPlayersUseCase: GetPlayersUseCase,
) : ViewModel() {
    private val _playersState: MutableStateFlow<PagingData<PlayerEntity>> =
        MutableStateFlow(value = PagingData.empty())
    val playersState: MutableStateFlow<PagingData<PlayerEntity>> get() = _playersState

    init {
        onEvent(PlayersEvent.Init)
    }

    private fun onEvent(event: PlayersEvent) {
        viewModelScope.launch {
            when (event) {
                is PlayersEvent.Init -> {
                    getPlayers()
                }
            }
        }
    }

    private suspend fun getPlayers() {
        getPlayersUseCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _playersState.value = it
            }
    }
}

sealed class PlayersEvent {
    object Init : PlayersEvent()
}
