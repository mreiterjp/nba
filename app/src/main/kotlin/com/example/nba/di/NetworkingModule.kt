package com.example.nba.di

import com.example.nba.common.Constants.BASE_URL
import com.example.nba.common.Constants.TIMEOUT_SECONDS
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Koin module holding network communication classes.
 */

val networkingModule =
    module {
        single {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build()
        }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
        }
    }
