package com.example.nba.di

import com.example.nba.presentation.view.player.detail.PlayerViewModel
import com.example.nba.presentation.view.player.list.PlayersViewModel
import com.example.nba.presentation.view.team.detail.TeamViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * For proper injection in compose see
 * https://insert-koin.io/docs/reference/koin-android/compose/
 */
val viewModelModule =
    module {
        viewModel {
            PlayersViewModel(
                getPlayersUseCase = get(),
            )
        }
        viewModel { (id: Int) ->
            PlayerViewModel(
                getPlayerUseCase = get(),
                id = id,
            )
        }
        viewModel { (id: Int) ->
            TeamViewModel(
                getTeamUseCase = get(),
                id = id,
            )
        }
    }
