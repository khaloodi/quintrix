/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {

        // todo (01) Define a variable, tonight, to hold the current night, and make it MutableLiveData:
        private var tonight = MutableLiveData<SleepNight?>()

        // todo (02) Define a variable, nights. Then getAllNights() from the database and assign to the nights variable:
        private val nights = database.getAllNights()

        val nightsString = Transformations.map(nights) { nights ->
                formatNights(nights, application.resources)
        }

        val startButtonVisible = Transformations.map(tonight) {
                null == it
        }

        val stopButtonVisible = Transformations.map(tonight) {
                null != it
        }

        val clearButtonVisible = Transformations.map(nights) {
                it?.isNotEmpty()
        }

        private var _showSnackBarEvent = MutableLiveData<Boolean>()

        val showSnackBarEvent: LiveData<Boolean>
                get() = _showSnackBarEvent

        private val _navigateToSleepQuality = MutableLiveData<SleepNight>()

        val navigateToSleepQuality: LiveData<SleepNight>
                get() = _navigateToSleepQuality

        fun doneNavigating() {
                _navigateToSleepQuality.value = null
        }

        fun doneShowingSnackBar() {
                _showSnackBarEvent.value = false
        }

        // todo (03) To initialize the tonight variable, create an init block and call initializeTonight(),
        //  which you'll define in the next step:
        init {
                initializeTonight()
        }

        // todo (04) Implement initializeTonight().
        //  Use the viewModelScope.launch{} to start a coroutine in the ViewModelScope.
        private fun initializeTonight() {
                viewModelScope.launch {
                        // todo (04 a.)get the value for tonight from the database by calling
                        //  getTonightFromDatabase(), which you will define in the next step,
                        //  and assign it to tonight.value:
                        tonight.value = getTonightFromDatabase()
                }
        }

        // todo (05) Implement getTonightFromDatabase(). Define is as a private suspend function
        //  that returns a nullable SleepNight, if there is no current started sleepNight
        private suspend fun getTonightFromDatabase():  SleepNight? {
                // Let the coroutine get tonight from the database. If the start and end times
                // are the not the same, meaning, the night has already been completed, return null.
                // Otherwise, return night:
                var night = database.getTonight()

                if (night?.endTimeMilli != night?.startTimeMilli) {
                        night = null
                }
                return night
        }

        // todo (06) Implement onStartTracking(), the click handler for the Start button:
        fun onStartTracking() {
                // todo (06 a.) Inside onStartTracking(), launch a coroutine in viewModelScope:
                viewModelScope.launch {
                        // todo (06 b.) Inside the coroutine, create a new SleepNight, which
                        //  captures the current time as the start time:
                        val newNight = SleepNight()
                        // Call insert() to insert it into the database. You will define insert() shortly:
                        insert(newNight)
                        // Set tonight to the new night:
                        tonight.value = getTonightFromDatabase()
                }
        }

        // Define insert() as a private suspend function that takes a SleepNight as its argument:
        private suspend fun insert(night: SleepNight) {
                // insert the night into the database:
                database.insert(night)
        }

        // todo Add onStopTracking() to the view model. Launch a coroutine in the viewModelScope.
        //  If it hasn't been set yet, set the endTimeMilli to the current system time and call
        //  update() with the night. There are several ways to implement this, and one is shown below:
        fun onStopTracking() {
                viewModelScope.launch {
                        val oldNight = tonight.value ?: return@launch
                        oldNight.endTimeMilli = System.currentTimeMillis()
                        update(oldNight)
                        _navigateToSleepQuality.value = oldNight
                }
        }

        // Implement update() using the same pattern as insert():
        private suspend fun update(night: SleepNight) {
                database.update(night)
        }

        // todo Analogously, implement onClear() and clear():
        fun onClear() {
                viewModelScope.launch {
                        clear()
                        tonight.value = null

                        _showSnackBarEvent.value = true
                }
        }

        suspend fun clear() {
                database.clear()
        }


}

