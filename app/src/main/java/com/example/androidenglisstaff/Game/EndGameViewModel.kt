package com.example.androidenglisstaff.Game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EndGameViewModel(finalScore: String):ViewModel() {


    private val _score = MutableLiveData<String>()
    val score: LiveData<String>
            get() = _score

    init {
        _score.value = finalScore
    }
}