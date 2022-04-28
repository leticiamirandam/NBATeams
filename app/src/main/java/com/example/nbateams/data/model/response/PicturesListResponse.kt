package com.example.nbateams.data.model.response

import com.google.gson.annotations.SerializedName

data class PicturesListResponse(
    @SerializedName("range")
    var range: String,
    @SerializedName("majorDimension")
    var majorDimension: String,
    @SerializedName("values")
    var values: List<List<String>>
)