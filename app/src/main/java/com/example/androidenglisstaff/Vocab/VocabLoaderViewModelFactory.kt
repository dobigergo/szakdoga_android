package com.example.androidenglisstaff.Vocab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidenglisstaff.Game.EndGameViewModel
import java.lang.IllegalArgumentException


class VocabLoaderViewModelFactory(private val topicName: String): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(VocabLoaderViewModel::class.java)){
            return  VocabLoaderViewModel(topicName) as T
        }
        throw IllegalArgumentException("Hali")
    }
}

