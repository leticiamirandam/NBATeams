package com.example.nbateams.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayersDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("height_feet")
    val heightFeet: Int,
    @SerializedName("height_inches")
    val heightInches: Int,
    @SerializedName("weight_pounds")
    val weightPounds: Int,
    @SerializedName("team")
    val team: TeamDetailResponse
): Parcelable