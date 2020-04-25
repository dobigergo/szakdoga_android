package com.example.androidenglisstaff.model

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


data class Word(

    var word1: String,
    var language1: Long,
    var word2: String,
    var language2:Long

)