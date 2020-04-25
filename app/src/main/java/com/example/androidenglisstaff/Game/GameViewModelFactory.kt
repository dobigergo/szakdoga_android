package com.example.androidenglisstaff.Game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidenglisstaff.Vocab.VocabLoader
import java.lang.IllegalArgumentException

class GameViewModelFactory(private val topicName: String): ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java)){
            return  GameViewModel(topicName) as T
        }
        throw IllegalArgumentException("Hali")
    }

}