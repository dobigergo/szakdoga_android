package com.example.languageLearning.Database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "score_table",
    foreignKeys = arrayOf(ForeignKey(
    entity = Player::class,
    parentColumns = arrayOf("playerName"),
    childColumns = arrayOf("player"),
    onDelete = ForeignKey.CASCADE)))
data class Score (

    @PrimaryKey(autoGenerate = true)
    var scoreId: Long = 0L,
    var player: String = "",
    var score: Int = 0,
    var date : Date
)