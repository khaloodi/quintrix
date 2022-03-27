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

package com.example.android.trackmysleepquality.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao // all Dao's need to be annotated with the Dao keyword
interface SleepDatabaseDao {
    // * add an annotated insert() method for inserting a single SleepNight
    @Insert // we need two things, an insert annotation and a function
    // definition for an insert function that we can call from our code
    fun insert(night: SleepNight) // insert function definition
    // during compilation, room will generate code to turn this passed in
    // Kotlin object "night" into a row of values for the database;
    // room creates the row from the entity object and inserts it into the DB

    // * add an annotated upddate() method for updating a SleepNight
    @Update
    fun update(night: SleepNight) // notice Insert and Update are simple
    // for the rest we'll have to write SQL queries to perform the specific operations
}

