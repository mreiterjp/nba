package com.example.nba.presentation.view.team.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nba.domain.entity.TeamEntity
import com.example.nba.domain.usecase.GetTeamUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * A view model that manages the data for a team.
 *
 * This class provides a way to fetch and observe team data, and it also exposes a state flow
 * that represents the team's most recent information.
 */
class TeamViewModel(
    private val getTeamUseCase: GetTeamUseCase,
    private val id: Int,
) : ViewModel() {
    /**
     * A private mutable state flow that stores the team's most recent information.
     */
    private val teamStateInternal = MutableStateFlow(value = TeamEntity.generateFakeTeamEntity(-1))

    /**
     * An observable state flow that is externally exposed to represent the team's most recent information.
     */
    val teamState: MutableStateFlow<TeamEntity> get() = teamStateInternal

    /**
     * Initializes the view model by fetching the team's data from the API.
     */
    init {
        onEvent(TeamEvent.Init)
    }

    /**
     * Handles a team event.
     *
     * @param event The team event to handle.
     */
    private fun onEvent(event: TeamEvent) {
        viewModelScope.launch {
            when (event) {
                is TeamEvent.Init -> {
                    getTeam(id)
                }
            }
        }
    }

    /**
     * Fetches the team's data from the API and updates the team's state flow accordingly.
     *
     * @param id The ID of the team to fetch data for.
     */
    private suspend fun getTeam(id: Int) {
        getTeamUseCase(id)
            .collect {
                teamStateInternal.value = it
            }
    }
}

/**
 * A sealed class that represents team events.
 *
 * This class is used to represent events that can occur in the `TeamViewModel` class.
 */
sealed class TeamEvent {
    // Indicates that the view model should fetch the team's data from the API.
    object Init : TeamEvent()
}
