package com.example.nbateams.domain.model

data class TeamsList(
    val teams: List<Team>
) {
    data class Team(
        val id: Int,
        val abbreviation: String,
        val city: String,
        val conference: String,
        val division: String,
        val fullName: String,
        val name: String,
        val picture: String
    )
}