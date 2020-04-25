package com.example.androidenglisstaff.Vocab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidenglisstaff.model.Word


class VocabLoaderViewModel(topicName: String) : VocabLoader(topicName) {

    private val wordList_ = MutableLiveData<List<Word>>()
    val wordList: LiveData<List<Word>>
        get() = wordList_


    fun setList(){
        wordList_.value=getVocab()
    }
}