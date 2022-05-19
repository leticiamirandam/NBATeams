package com.example.nbateams.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teamcm")
data class TeamCM(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "abbreviation") val abbreviation: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "conference") val conference: String,
    @ColumnInfo(name = "division") val division: String,
    @ColumnInfo(name = "fullName") val fullName: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "picture") val picture: String
)