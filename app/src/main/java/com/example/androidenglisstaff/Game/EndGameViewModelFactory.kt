package com.example.androidenglisstaff.Game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class EndGameViewModelFactory(private val finalScore: String): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EndGameViewModel::class.java)){
            return  EndGameViewModel(finalScore) as T
        }
        throw IllegalArgumentException("Hali")
    }


}