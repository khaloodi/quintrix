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

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao // all Dao's need to be annotated with the Dao keyword
interface SleepDatabaseDao {
    // * add an annotated insert() method for inserting a single SleepNight
    @Insert // we need two things, an insert annotation and a function
    // definition for an insert function that we can call from our code
    fun insert(night: SleepNight) // insert function definition
    // during compilation, room will generate code to turn this passed in
    // Kotlin object "night" into a row of values for the database;
    // room creates the row from the entity object and inserts it into the DB

    // * add an annotated update() method for updating a SleepNight
    @Update
    fun update(night: SleepNight) // notice Insert and Update are simple
    // for the rest we'll have to write SQL queries to perform the specific operations

    // add annotated get() method that gets the SleepNight by key
    @Query ("SELECT*FROM daily_sleep_quality_table WHERE nightId = :key") // nightId column is the reference in the DB
    // and the ":key" colon parameter is how we reference a parameter from the function
    fun get(key: Long): SleepNight // get, pass in primary key "Long" type, and respond with the
    // corresponding table row as an instance of "SleepNight", remember to add SQL query above as a string
    // parameter to query

    // clear the database by deleting all rows w/o deleting the table
    // add annotated clear() method and query
    // can use the @Delete annotation which deletes a single entity and do the choosing
    // in the SQL code
//    @Delete                   METHOD 1
//    fun delete(night: SleepNight)    // this is not efficient ******
    // instead, can supply a list of nights to the @Delete annotation
//    @Delete                   METHOD 2
//    fun deleteAllNights(nights: List<SleepNight>): Int // the function returns the number of rows,
    // actually deleted
            // downfall of this is you have to know what's inside
            // of the database, this great for deleting specific entries
            // but not efficient for clearing ******
    // since we don't care what's in table, we can just delete all
    @Query("DELETE FROM daily_sleep_quality_table") // SQL query w/no WHERE constraint
    fun clear() // clear method to delete all from table

    // return a list of sorted entities
    @Query("SELECT*FROM daily_sleep_quality_table ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>> // get back a list of SleepNight, however, it's actually livedata!
    // room ensures that this LiveData is updated whenever the database is updated, therefore
    // we only need to get this list of all nights once, attach an observer to it, and then if the data
    // changes, the UI will update itself to show the changed data, w/o us having to get the data again
    // feature saves time, code, complexity, and debugging time

    // add anotated getTonight() method and query
    // in other words, return the most recent night
    @Query("SELECT*FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1" )
    fun getTonight(): SleepNight? // the return type, SleepNight is nullable because in the beginning and
    // after we clear all the contents, there is no tonight
}

