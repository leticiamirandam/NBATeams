package com.example.nbateams.di

import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.datasource.*
import com.example.nbateams.data.datasource.PlayerDetailRemoteDataSource
import com.example.nbateams.data.datasource.PlayersListRemoteDataSource
import com.example.nbateams.data.datasource.PlayersListRemoteDataSourceImpl
import com.example.nbateams.data.datasource.TeamsListRemoteDataSource
import com.example.nbateams.data.datasource.TeamsListRemoteDataSourceImpl
import com.example.nbateams.data.mapper.PlayerMapper
import com.example.nbateams.data.mapper.PlayersListMapper
import com.example.nbateams.data.mapper.TeamMapper
import com.example.nbateams.data.mapper.TeamsListMapper
import com.example.nbateams.data.repository.PlayerDetailRepositoryImpl
import com.example.nbateams.data.repository.PlayersListRepositoryImpl
import com.example.nbateams.data.repository.TeamDetailRepositoryImpl
import com.example.nbateams.data.repository.TeamsListRepositoryImpl
import com.example.nbateams.domain.repository.PlayerDetailRepository
import com.example.nbateams.domain.repository.PlayersListRepository
import com.example.nbateams.domain.repository.TeamDetailRepository
import com.example.nbateams.domain.repository.TeamsListRepository
import com.example.nbateams.domain.usecase.GetPlayerDetailUseCase
import com.example.nbateams.domain.usecase.GetPlayersListUseCase
import com.example.nbateams.domain.usecase.GetTeamDetailUseCase
import com.example.nbateams.domain.usecase.GetTeamsListUseCase
import com.example.nbateams.presentation.playerdetail.PlayerDetailViewModel
import com.example.nbateams.presentation.playerslist.PlayersListViewModel
import com.example.nbateams.presentation.teamdetail.TeamDetailViewModel
import com.example.nbateams.presentation.teamslist.TeamsListViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private var BASE_URL = "https://www.balldontlie.io/api/v1/"

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
    factory { GetPlayerDetailUseCase(get()) }
    factory { GetTeamDetailUseCase(get()) }
}

val dataModule = module {
    factory<PlayersListRemoteDataSource> { PlayersListRemoteDataSourceImpl(get()) }
    factory<TeamsListRemoteDataSource> { TeamsListRemoteDataSourceImpl(get()) }
    factory<PlayerDetailRemoteDataSource> { PlayerDetailRemoteDataSourceImpl(get()) }
    factory<TeamDetailRemoteDataSource> { TeamDetailRemoteDataSourceImpl(get()) }
    factory<PlayersListRepository> { PlayersListRepositoryImpl(get()) }
    factory<TeamsListRepository> { TeamsListRepositoryImpl(get(), TeamsListMapper()) }
    factory<PlayerDetailRepository> { PlayerDetailRepositoryImpl(get(), PlayerMapper()) }
    factory<TeamDetailRepository> { TeamDetailRepositoryImpl(get(), TeamMapper()) }
}

val presentationModule = module {
    viewModel {
        TeamsListViewModel(
            getTeamsListUseCase = get()
        )
    }
    viewModel {
        PlayersListViewModel(
            playersListUseCase = get(),
            playerMapper = PlayerMapper()
        )
    }
    viewModel { (id: Int) ->
        PlayerDetailViewModel(
            playerId = id,
            getPlayerDetailUseCase = get()
        )
    }
    viewModel { (id: Int) ->
        TeamDetailViewModel(
            teamId = id,
            getTeamDetailUseCase = get()
        )
    }
}