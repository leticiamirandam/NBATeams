package com.example.nbateams.data.datasource

import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.api.PictureService
import com.example.nbateams.data.model.response.PicturesListResponse
import com.example.nbateams.data.model.response.TeamsListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class TeamsListRemoteDataSourceImpl(
    private val nbaService: NBAService,
    private val pictureService: PictureService
) : TeamsListRemoteDataSource {
    override fun getTeamsList(): Flow<TeamsListResponse> = flow {
        emit(nbaService.getTeamsList())
    }

    override fun getTeamsPictures(): Flow<PicturesListResponse> = flow {
        emit(pictureService.getPicturesList())
    }
}