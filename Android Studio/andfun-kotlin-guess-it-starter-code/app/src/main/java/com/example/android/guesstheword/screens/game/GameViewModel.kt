package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

// TODO create the GameViewModel class, extending ViewModel
class GameViewModel : ViewModel() {
    // TODO (03) Add init and override onCleared; Add log statements to both
    init {
        Log.i("GameViewModel", "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "hi from onCleared")
    }
}