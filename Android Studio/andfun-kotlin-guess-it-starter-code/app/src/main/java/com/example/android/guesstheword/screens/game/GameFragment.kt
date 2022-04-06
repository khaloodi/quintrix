/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("SyntaxError")

package com.example.android.guesstheword.screens.game

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel // instantiate GameViewModel
    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        // Get the view model
        Log.i("GameFragment", "Called ViewModelProvider!")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        // Set the viewmodel for data binding - this allows the bound layout access to all of the
        // data in the ViewModel
        binding.gameViewModel = viewModel // binding viewModel to layout

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.setLifecycleOwner(this) // allows us to use data binding to automatically update our layout

        /** ON CLICK LISTENERS ARE REMOVED BECAUSE THEY ARE LAMBDAS IN THE XML NOW
        //binding.correctButton.setOnClickListener { onCorrect() }
        //binding.skipButton.setOnClickListener { onSkip() }
        // have to call viewModel version
        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
            // updateScoreText() todo this was removed after we created the viewModel and passed in the observer object
            // updateWordText() todo this was removed after we created the viewModel and passed in the observer object
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
            // updateScoreText() todo this was removed after we created the viewModel and passed in the observer object
            // updateWordText() todo this was removed after we created the viewModel and passed in the observer object
        }
        */

        // TODO () pass in viewModel w/observe... observe takes two things, first is a lifecycle owner,
        //  the UI controller that's associated with the life data... -we pass in this or the fragment itself
        //  second is an anonymous observer object, which is essentially the code that is going to get triggered
        //  any time the live data changes
        /** Setting up LiveData observation relationship **/

        /** todo this is removed b/c we are directly referencing it in the text field w/in game_fragment.xml
         * this is part of the magic of data binding after we added the "binding.setLifecycleOwner(this)"
         *
        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })
        */

        /** todo this is removed b/c we are directly referencing it in the text field w/in game_fragment.xml
         * this is part of the magic of data binding after we added the "binding.setLifecycleOwner(this)"
         *
        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = newWord
        })
        */

        /** todo this is removed b/c we are directly referencing it in the text field w/in game_fragment.xml
         * this is part of the magic of data binding after we added the "binding.setLifecycleOwner(this)"
         *
        viewModel.currentTime.observe(viewLifecycleOwner, Observer { newTime ->
            // binding.timerText.text = DateUtils.formatElapsedTime(newTime) --- use a lambda function in view model to do this instead

        })
        */
        // Sets up event listening to navigate the player when the game is finished
        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished) {
                val currentScore = viewModel.score.value ?: 0
                val action = GameFragmentDirections.actionGameToScore(currentScore)
                findNavController(this).navigate(action)
                viewModel.onGameFinishComplete()
            }
        })

        // Buzzes when triggered with different buzz events
        viewModel.eventBuzz.observe(viewLifecycleOwner, Observer { buzzType ->
            if (buzzType != GameViewModel.BuzzType.NO_BUZZ) {
                buzz(buzzType.pattern)
                viewModel.onBuzzComplete()
            }
        })

        // updateScoreText() todo this was removed after we created the viewModel and passed in the observer object
        // updateWordText() todo this was removed after we created the viewModel and passed in the observer object
        return binding.root

    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }

}
    /**
     * Called when the game is finished

    private fun gameFinished() {
        // val action = GameFragmentDirections.actionGameToScore(score)
        // val action = GameFragmentDirections.actionGameToScore(viewModel.score) todo have to change this b/c score is a live data now
        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?: 0 ) // says if score is ever null, which it never should be, to pass in 0
        // acton.setScore(currentScore)
        findNavController(this).navigate(action)
        Toast.makeText(this.activity, "Game has finished", Toast.LENGTH_SHORT).show()
    }
     */

    /** Methods for buttons presses **/
// Moved over to GameViewModel.kt
//    private fun onSkip() {
//        score--
//        nextWord()
//    }
//
//    private fun onCorrect() {
//        score++
//        nextWord()
//    }

    /** Methods for updating the UI
    TODO THE BINDING intiializations get moved into the observer above
    private fun updateWordText() {
        // binding.wordText.text = word
        binding.wordText.text = viewModel.word

    }

    private fun updateScoreText() {
        // binding.scoreText.text = score.toString()
        binding.scoreText.text = viewModel.score.toString()
    }
     **/

    // Given a pattern, this method will actually perform the buzz.
    // It uses the activity to get a system service:

