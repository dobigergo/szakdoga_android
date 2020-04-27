package com.example.languageLearning.Topic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.languageLearning.Service.TopicApi
import com.example.languageLearning.model.Topic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class TopicLoader: ViewModel(){

    private lateinit var topic : List<Topic>

    private val success_ = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = success_

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    fun getTopics() : List<Topic>{
        return topic
    }

    init{
        success_.value=false
        getTopicProperties()
    }

    fun getTopicProperties() {

        coroutineScope.launch {
            setup()
            success_.value=true
        }
    }

    private suspend fun setup(){
        var getPropertiesDeferred = TopicApi.retrofitService.getProperties()

        try {
            topic = getPropertiesDeferred.await()
            _response.value = "Success: ${topic.size} Mars properties retrieved"
        } catch (e: Exception) {
            println(e.toString())
            setup()
        }
    }


}