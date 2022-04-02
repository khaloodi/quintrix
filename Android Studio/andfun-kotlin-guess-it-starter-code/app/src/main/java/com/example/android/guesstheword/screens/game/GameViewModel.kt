package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// TODO (00) create the GameViewModel class, extending ViewModel
class GameViewModel : ViewModel() {
    // TODO (00) Move over the word, score and wordList variables to the GameViewModel
    //  and remove private keyword
    // The current word
    // var word = "" --- change this to live data
    val word = MutableLiveData<String>()

    // The current score
    // var score = 0
    // TODO ^^ Convert score to be a mutable live data:
    val score = MutableLiveData<Int>() // live data object will always stay the same, although the
    // data in it might change... that's what makes it MutableLiveData

    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>
    // TODO (00) Add init and override onCleared; Add log statements to both
    init {
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()

        // because we don't want our mutable live data to be null, we initialize the following:
        score.value = 0
        word.value = ""
    }

    fun onSkip() {
        // score--
        // have to change this ^^^ b/c score is now a live data, and handle nulls:
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        // score++
        score.value = (score.value)?.plus(1)
        nextWord()
    }

    /**
     * Resets the list of words and randomizes the order
     */
    fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            // gameFinished()
        } else {
            word.value = wordList.removeAt(0)
        }
        // TODO Removed these two methods from GameViewModel.kt, they are in GameFragment.kt
        // updateWordText()
        // updateScoreText()
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "hi from onCleared")
    }
}