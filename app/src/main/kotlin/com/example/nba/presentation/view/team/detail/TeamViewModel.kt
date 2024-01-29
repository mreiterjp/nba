package com.example.nba.presentation.view.team.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nba.domain.entity.TeamEntity
import com.example.nba.domain.usecase.GetTeamUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TeamViewModel(
    private val getTeamUseCase: GetTeamUseCase,
    private val id: Int,
) : ViewModel() {
    private val _teamState: MutableStateFlow<TeamEntity> =
        MutableStateFlow(value = TeamEntity.generateFakeTeamEntity(-1))
    val teamState: MutableStateFlow<TeamEntity> get() = _teamState

    init {
        onEvent(TeamEvent.Init)
    }

    private fun onEvent(event: TeamEvent) {
        viewModelScope.launch {
            when (event) {
                is TeamEvent.Init -> {
                    getTeam(id)
                }
            }
        }
    }

    private suspend fun getTeam(id: Int) {
        getTeamUseCase(id)
            .collect {
                _teamState.value = it
            }
    }
}

sealed class TeamEvent {
    object Init : TeamEvent()
}
