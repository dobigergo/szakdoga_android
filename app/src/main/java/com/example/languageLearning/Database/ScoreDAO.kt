package com.example.languageLearning.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScoreDAO {

    @Insert
    fun insert(score : Score)

    @Update
    fun update(score: Score)

    @Query("Select * from score_table where scoreId=:key ")
    fun get(key: Long): Score?

    @Query("Delete From score_table")
    fun clear()

    @Query("Select * from score_table order by date desc")
    fun getAll(): LiveData<List<Score>>

    @Query("select * from score_table where player = :key")
    fun getAll(key:String):LiveData<List<Score>>

}