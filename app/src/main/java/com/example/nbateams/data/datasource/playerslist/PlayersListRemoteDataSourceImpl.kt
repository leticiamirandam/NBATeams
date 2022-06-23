package com.example.nbateams.data.datasource.playerslist

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.model.response.PlayersDetailResponse
import com.example.nbateams.presentation.util.NbaPagingSource
import kotlinx.coroutines.flow.Flow

internal class PlayersListRemoteDataSourceImpl(
    private val nbaService: NBAService
): PlayersListRemoteDataSource {
    override fun getPlayersList(): Flow<PagingData<PlayersDetailResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NbaPagingSource(service = nbaService)
            }
        ).flow
    }
}