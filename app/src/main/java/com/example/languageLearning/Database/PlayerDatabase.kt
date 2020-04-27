package com.example.languageLearning.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class PlayerDatabase: RoomDatabase() {

    abstract val playerDao: PlayerDAO
    //abstract val scoreDao: ScoreDAO

    companion object{
        @Volatile
        private var INSTANCE: PlayerDatabase? = null
        fun getInstance(context: Context): PlayerDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance=Room
                        .databaseBuilder(context.applicationContext,PlayerDatabase::class.java,"player_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}