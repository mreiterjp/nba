package com.example.nba.di

import com.example.nba.data.repository.PlayerRepository
import com.example.nba.data.repository.TeamRepository
import org.koin.dsl.module

/**
 * Koin module holding repositories
 */
val repositoryModule = module {
    single {
        PlayerRepository(playerApi = get())
    }

    single {
        TeamRepository(teamApi = get())
    }
}
