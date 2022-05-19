package com.example.nbateams.di

import android.app.Application
import androidx.room.Room
import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.api.PictureService
import com.example.nbateams.data.cache.mapper.CacheToDomainMapper
import com.example.nbateams.data.cache.mapper.RemoteToCacheMapper
import com.example.nbateams.data.cache.room.TeamDB
import com.example.nbateams.data.cache.room.TeamDao
import com.example.nbateams.data.datasource.*
import com.example.nbateams.data.mapper.PlayerMapper
import com.example.nbateams.data.mapper.TeamMapper
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
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.balldontlie.io/api/v1/"
private const val PICTURES_URL = "https://sheets.googleapis.com/v4/spreadsheets/"
private const val DB_NAME = "TEAMDB"

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
}

val nbaNetworkModule = module {
    single<Retrofit>(named("nba")) {
        get<Retrofit.Builder>()
            .baseUrl(BASE_URL)
            .build()
    }
    single<NBAService> {
        get<Retrofit>(named("nba")).create(NBAService::class.java)
    }
}

val networkPictureModule = module {
    single<Retrofit>(named("pictures")) {
        get<Retrofit.Builder>()
            .baseUrl(PICTURES_URL)
            .build()
    }
    single<PictureService> {
        get<Retrofit>(named("pictures")).create(PictureService::class.java)
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
    factory<TeamsListRemoteDataSource> { TeamsListRemoteDataSourceImpl(get(), get()) }
    factory<PlayerDetailRemoteDataSource> { PlayerDetailRemoteDataSourceImpl(get()) }
    factory<TeamDetailRemoteDataSource> { TeamDetailRemoteDataSourceImpl(get()) }
    factory<PlayersListRepository> { PlayersListRepositoryImpl(get(), PlayerMapper()) }
    factory<TeamsListRepository> {
        TeamsListRepositoryImpl(get(), get(), RemoteToCacheMapper(), CacheToDomainMapper())
    }
    factory<PlayerDetailRepository> { PlayerDetailRepositoryImpl(get(), PlayerMapper()) }
    factory<TeamDetailRepository> {
        TeamDetailRepositoryImpl(get(), get(), TeamMapper(), CacheToDomainMapper())
    }
}

val teamsDB = module {
    fun provideDataBase(application: Application): TeamDB {
        return Room.databaseBuilder(application, TeamDB::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: TeamDB): TeamDao {
        return dataBase.teamDao()
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}

val presentationModule = module {
    viewModel {
        TeamsListViewModel(
            getTeamsListUseCase = get()
        )
    }
    viewModel {
        PlayersListViewModel(
            playersListUseCase = get()
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