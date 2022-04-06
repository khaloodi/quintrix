package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

// TODO (00) create the GameViewModel class, extending ViewModel
class GameViewModel : ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000L // set to 10 seconds
    }

    private val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    val currentTimeString = Transformations.map(currentTime, { time ->
        DateUtils.formatElapsedTime(time)
    })

    // TODO (00) Move over the word, score and wordList variables to the GameViewModel
    //  and remove private keyword
    // The current word
    // var word = "" --- change this to live data
    // val word = MutableLiveData<String>()
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // The current score
    // var score = 0
    // TODO ^^ Convert score to be a mutable live data:
    // val score = MutableLiveData<Int>() // live data object will always stay the same, although the
    // todo, add encapsulation to modify the getter of  score outside of this, make it a LiveData so it can't be changed
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score
    //  and change the reference to private w/an underscore
    // data in it might change... that's what makes it MutableLiveData

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    // Event which triggers the end of the game
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish


    // TODO (00) Add init and override onCleared; Add log statements to both
    init {
        Log.i("GameViewModel", "GameViewModel created!")
        // _eventGameFinish.value = false
        resetList()
        nextWord()

        // because we don't want our mutable live data to be null, we initialize the following:
        // score.value = 0
        _score.value = 0
        // word.value = ""
        // _word.value = ""

        // creates a timer object which triggers the end of the game when it finishes
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                // TODO implement what should happen each tick of the timer
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                // TODO implement what should happen when the timer finishes
                _currentTime.value = DONE
                _eventGameFinish.value = true
            }
        }

        // DateUtils.formatElapsedTime(newTime)
        timer.start()
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
            // _eventGameFinish.value = true
            // todo instead of ^ ending the game... reset the word list
            resetList()
        } // else {
        // word.value = wordList.removeAt(0)
        _word.value = wordList.removeAt(0)
        // }
        // TODO Removed these two methods from GameViewModel.kt, they are in GameFragment.kt
        // updateWordText()
        // updateScoreText()
    }

    /** Methods for buttons presses **/
    fun onSkip() {
        // score--
        // have to change this ^^^ b/c score is now a live data, and handle nulls:
        // score.value = (score.value)?.minus(1)
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        // score++
        // score.value = (score.value)?.plus(1)
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    /** Methods for completed events **/

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        // To avoid memory leaks, you should always cancel a CountDownTimer if you no longer need it:
        timer.cancel()
        Log.i("GameViewModel", "hi from onCleared")
    }
}