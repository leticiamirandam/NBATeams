package com.example.nbateams.di

import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.datasource.PlayersListRemoteDataSource
import com.example.nbateams.data.datasource.PlayersListRemoteDataSourceImpl
import com.example.nbateams.data.datasource.TeamsListRemoteDataSourceImpl
import com.example.nbateams.data.datasource.TeamsListRemoteDataSource
import com.example.nbateams.data.mapper.PlayersListMapper
import com.example.nbateams.data.mapper.TeamsListMapper
import com.example.nbateams.data.repository.PlayersListRepositoryImpl
import com.example.nbateams.data.repository.TeamsListRepositoryImpl
import com.example.nbateams.domain.repository.PlayersListRepository
import com.example.nbateams.domain.repository.TeamsListRepository
import com.example.nbateams.domain.usecase.GetPlayersListUseCase
import com.example.nbateams.domain.usecase.GetTeamsListUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private var BASE_URL = "https://pokeapi.co/api/v2/"

val networkModule = module {
    single<Gson> { GsonBuilder().create() }
    single {
        OkHttpClient.Builder()
            .build()
    }
    single<GsonConverterFactory> { GsonConverterFactory.create(get()) }
    single<RxJava2CallAdapterFactory> {
        RxJava2CallAdapterFactory.create()
    }
    single<Retrofit.Builder> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
    }
    single<Retrofit> {
        get<Retrofit.Builder>()
            .baseUrl(BASE_URL)
            .build()
    }
    single<NBAService> {
        get<Retrofit>().create(NBAService::class.java)
    }
}

val domainModule = module {
    factory { GetPlayersListUseCase(get()) }
    factory { GetTeamsListUseCase(get()) }
}

val dataModule = module {
    factory<PlayersListRemoteDataSource> { PlayersListRemoteDataSourceImpl(get()) }
    factory<TeamsListRemoteDataSource> { TeamsListRemoteDataSourceImpl(get()) }
    factory<PlayersListRepository> { PlayersListRepositoryImpl(get(), PlayersListMapper()) }
    factory<TeamsListRepository> { TeamsListRepositoryImpl(get(), TeamsListMapper()) }
}