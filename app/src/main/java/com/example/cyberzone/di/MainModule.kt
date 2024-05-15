package com.example.cyberzone.di

import android.app.Application
import android.content.Context
import com.example.cyberzone.network.BoardGamesApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.brotli.BrotliInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideBoardApiService(): BoardGamesApiService {
        val BASE_URL = "https://esports.mirea.ru/api/v1/"

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(BrotliInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()

        val retrofitService: BoardGamesApiService by lazy {
            retrofit.create(BoardGamesApiService::class.java)
        }

        return retrofitService
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}