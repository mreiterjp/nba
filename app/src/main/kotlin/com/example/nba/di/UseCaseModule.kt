package com.example.nba.di

import com.example.nba.domain.usecase.GetPlayerUseCase
import com.example.nba.domain.usecase.GetPlayersUseCase
import com.example.nba.domain.usecase.GetTeamUseCase
import org.koin.dsl.module

/**
 * Koin module holding use case definitions.
 */
val useCaseModule =
    module {
        single { GetPlayerUseCase(playerRepository = get()) }
        single { GetPlayersUseCase(playerRepository = get()) }
        single { GetTeamUseCase(teamRepository = get()) }
    }
