package com.example.languageLearning.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlayerDAO {

    @Insert
    fun insert(score : Score)

    @Update
    fun update(score: Score)

    @Query("Select * from player_name where playerId=:key ")
    fun get(key: Long): Player?

    @Query("Select * from player_name where playerName=:key ")
    fun get(key: String): Player?

    @Query("Delete From player_name")
    fun clear()

    @Query("Select * from player_name")
    fun getAll(): LiveData<List<Player>>


}