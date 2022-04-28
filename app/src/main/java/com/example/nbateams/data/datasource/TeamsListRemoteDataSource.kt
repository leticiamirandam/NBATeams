package com.example.nbateams.data.datasource

import com.example.nbateams.data.model.response.PicturesListResponse
import com.example.nbateams.data.model.response.TeamsListResponse
import kotlinx.coroutines.flow.Flow

internal interface TeamsListRemoteDataSource {
    fun getTeamsList(): Flow<TeamsListResponse>
    fun getTeamsPictures(): Flow<PicturesListResponse>
}