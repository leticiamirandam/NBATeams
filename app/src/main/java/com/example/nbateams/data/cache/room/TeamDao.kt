package com.example.nbateams.data.cache.room

import androidx.room.*
import com.example.nbateams.data.cache.model.TeamCM

@Dao
interface TeamDao {
    @Query("SELECT * FROM teamcm")
    fun getAll(): List<TeamCM>

    @Query("SELECT * FROM teamcm WHERE id =:id")
    fun getTeamById(id: Int): TeamCM

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(teams: List<TeamCM>)

    @Delete
    fun delete(team: TeamCM)
}