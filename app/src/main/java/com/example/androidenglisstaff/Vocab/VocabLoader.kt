package com.example.androidenglisstaff.Vocab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidenglisstaff.Service.VocabApi
import com.example.androidenglisstaff.model.Word
import kotlinx.coroutines.*

open class VocabLoader(topic_name: String): ViewModel(){

    private lateinit var vocab : List<Word>

    private var name: String = topic_name

    private val success_ = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = success_

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    fun getVocab() : List<Word> {
        return vocab
    }

    init{
        success_.value=false
        getVocabProperties()
    }

    fun getVocabProperties() {

        coroutineScope.launch {
            setup()
            success_.value=true
        }
    }

    private suspend fun setup(){
        var getPropertiesDeferred = VocabApi.retrofitService.getProperties(name)

        try {
            vocab = getPropertiesDeferred.await()
            _response.value = "Success: ${vocab.size} Mars properties retrieved"
        } catch (e: Exception) {
            setup()
        }
    }

}