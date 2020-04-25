package com.example.androidenglisstaff.Database

import android.provider.Settings
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.security.AccessController.getContext

@Entity(tableName = "player_name")
data class Player(

    @PrimaryKey(autoGenerate = true)
    var playerId: Long = 0L,
    @ColumnInfo(name="playerName")
    var playerName: String = "",
    @ColumnInfo(name = "androidId")
    var androidId: String = Settings.Secure.ANDROID_ID
)