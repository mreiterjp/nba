package com.example.nba.di

import com.example.nba.data.api.PlayerApi
import com.example.nba.data.api.TeamApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

/**
 * Koin module holding API definitions.
 */
val apiModule = module {
    single<PlayerApi> { get<Retrofit>().create() }
    single<TeamApi> { get<Retrofit>().create() }
}
