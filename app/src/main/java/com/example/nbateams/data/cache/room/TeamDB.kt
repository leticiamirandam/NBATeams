package com.example.nbateams.data.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nbateams.data.cache.model.TeamCM

@Database(entities = [TeamCM::class], version = 1)
abstract class TeamDB : RoomDatabase(){
    abstract fun teamDao(): TeamDao
}