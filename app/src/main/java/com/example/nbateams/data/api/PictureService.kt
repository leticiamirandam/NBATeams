package com.example.nbateams.data.api

import com.example.nbateams.data.model.response.PicturesListResponse
import retrofit2.http.GET

interface PictureService {

    @GET("1MzSUKUUutanrm6MAVx0b1XIyb7ES7yCIccDQjQyLivA/values/A2:B31?key=AIzaSyAa_EwB4U61OZPAs8f99xlxkmz_vihwp0U")
    suspend fun getPicturesList(): PicturesListResponse
}