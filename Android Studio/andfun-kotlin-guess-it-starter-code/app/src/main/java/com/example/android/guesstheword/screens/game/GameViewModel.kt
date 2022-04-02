package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

// TODO (00) create the GameViewModel class, extending ViewModel
class GameViewModel : ViewModel() {
    // TODO (00) Move over the word, score and wordList variables to the GameViewModel
    //  and remove private keyword
    // The current word
    var word = ""

    // The current score
    var score = 0

    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>
    // TODO (00) Add init and override onCleared; Add log statements to both
    init {
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
    }

    fun onSkip() {
        score--
        nextWord()
    }

    fun onCorrect() {
        score++
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
            word = wordList.removeAt(0)
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