package com.example.nbateams.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamsListResponse(
    @SerializedName("data")
    val data: List<TeamDetailResponse>
) : Parcelable
