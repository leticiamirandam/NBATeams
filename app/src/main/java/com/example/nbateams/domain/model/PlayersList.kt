package com.example.nbateams.domain.model

data class PlayersList(
    val players: List<Player>
) {
    data class Player(
        val id: Int,
        val firstName: String,
        val lastName: String,
        val position: String,
        val heightFeet: String,
        val heightInches: String,
        val weightPounds: String,
        val team: TeamsList.Team
    )
}
