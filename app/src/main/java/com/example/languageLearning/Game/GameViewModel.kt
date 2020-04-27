package com.example.languageLearning.Game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.languageLearning.Vocab.VocabLoader
import com.example.languageLearning.model.Word


class GameViewModel(topicName: String) : VocabLoader(topicName) {

    private lateinit var wordList : MutableList<Word>
    //the current english word
    private var engWord = ""
    //the current score
    private val score_ = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = score_
    val scoreString = Transformations.map(score){score -> score.toString()}
    //the answer from the user
    private var answer = ""

    private val eventGameFinish_ = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get()=eventGameFinish_

    private val word_= MutableLiveData<Word>()
    val word : LiveData<Word>
        get() = word_


    private fun setList(){

        wordList =  getVocab().shuffled() as MutableList<Word>

    }

    init{
        wordList = listOf(Word("",1, "", 2)) as MutableList<Word>
        score_.value=0
    }

    fun start(){
        setList()
        nextWord()
    }

    fun onSubmit(answer : String){
        score_.value = score_.value?.plus(checkPoint(answer))
        nextWord()
    }

    private fun nextWord() {
        if(wordList.isNotEmpty()){
            word_.value=wordList.removeAt(0)
            engWord= word_.value!!.word1
        }
        else{
            onGameFinis()
        }
    }

    fun checkPoint(answer: String): Int {
        this.answer = answer
        val min = Math.min(engWord.length, answer.length)
        if (engWord.equals(answer))
            return 2
        else if (errorCnt(min) < 3)
            return 1
        return 0
    }

    fun onGameFinis(){
        eventGameFinish_.value = true
    }

    fun onGameFinishComplete() {
        eventGameFinish_.value = false
    }

    private fun errorCnt(min: Int): Int {
        var min = min
        var errorCnt = 0
        var answerChars = answer.toCharArray()
        val engWordChars = engWord.toCharArray()
        var charIndex = 0
        while (charIndex < min) {
            if (errorCnt == 3)
                return 3 + Math.abs(answerChars.size - engWordChars.size)
            if (answerChars[charIndex] != engWordChars[charIndex]) {
                errorCnt++
                if (answerChars.size < engWordChars.size) {
                    answerChars = addCharAt(charIndex, engWordChars[charIndex]).toCharArray()
                    charIndex--
                    min = answerChars.size
                } else if (answerChars.size > engWordChars.size) {
                    answerChars = removeCharAt(charIndex).toCharArray()
                    charIndex--
                }
            }
            charIndex++
        }
        return errorCnt + Math.abs(answerChars.size - engWordChars.size)
    }

    private fun removeCharAt(charIndexToRemove: Int): String {
        return answer.substring(0, charIndexToRemove) + answer.substring(charIndexToRemove + 1)
    }

    private fun addCharAt(indexToAddChar: Int, charToAdd: Char): String {
        return answer.substring(0, indexToAddChar) + charToAdd + answer.substring(indexToAddChar)
    }



}