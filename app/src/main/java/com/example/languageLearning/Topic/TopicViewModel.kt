package com.example.languageLearning.Topic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.languageLearning.model.Topic

class TopicViewModel : TopicLoader() {

    private val topicList_ = MutableLiveData<List<Topic>>()
    val topicList: LiveData<List<Topic>>
        get() = topicList_

    private val selectedTopic_ = MutableLiveData<String>()
    val selectedTopic: LiveData<String>
        get() = selectedTopic_

    private val isTopicSelected_ = MutableLiveData<Boolean>()
    val isTopicSelected: LiveData<Boolean>
        get() = isTopicSelected_


    init{
        isTopicSelected_.value = false
    }

    fun setTopic(topic: String){
        selectedTopic_.value=topic
        isTopicSelected_.value=true
    }

    fun setList(){
        topicList_.value = getTopics()
    }



}