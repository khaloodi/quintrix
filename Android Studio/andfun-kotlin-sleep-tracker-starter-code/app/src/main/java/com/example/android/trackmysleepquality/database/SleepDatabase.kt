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

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database // 2. annotate the class with the @Database annotation
@Database(entities = [SleepNight::class], version = 1, exportSchema = false) // 3.have to
// declare entities and version of db, there is only one table "[SleepNight::class]"
// ***** whenever you change the schema you have to change the version # *****

abstract class SleepDatabase : RoomDatabase(){ // 1. create an abstract class that extends RoomDatabase

    // declare an abstract value of type SleepNightDao; e.g. tell it about the DOA associated
    // with our entity so that it can interact w/the DB
    abstract val sleepDatabaseDao: SleepDatabaseDao // 4. declaring an abstract value of the type of our DAO

    // declare a companion object
    companion object { // 5. allows clients to access the methods for creating or getting
        // the db w/o instantiating the class, since the only purpose of this class is to
        // provide us with the database, there is no reason to ever instantiate it

        // declare a @Volatile INSTANCE variable
        @Volatile // 6. inside the companion object, we declare a private nullable variable
        // for the database, and initialize it to null:
        private var INSTANCE: SleepDatabase? = null // INSTANCE will keep a reference to the database
        // once we have one, which will prevent us from repeatedly opening connections to the database
        // which is an "expensive" operation
        // @Volatile annotation definition:
        // this helps us make sure the value of instance is always up to date and the same to
        // all execution threads the value of a volatile variable will never be cached and all writes
        // and reads will be done to and from the main memory, it means that changes made by one thread
        // to instance are visible to all other threads immediately
        // and we do get the situation where two threads each update the same entity in the cache
        // and cause a problem

        // 7. define a getInstance() method with a synchronized block
        fun getInstance(context: Context) : SleepDatabase { // returns a reference to SleepDatabase
            // 8. add a synchronized block
            synchronized(this) { // multiple threads can potentially ask for a DB instance
                // at the same time, leaving us w/2 instead of one... although unlikely in
                // these example apps
                // this ensures that only one thread of execution can enter this block at a time which
                // makes sure that the DB only gets initialized once
                // 9. copy the current value of instance to a local variable:
                var instance = INSTANCE // implement Kotlin's smartcast here by using a variable ***
                // side note, smart casing is only available to local variables not class variables

                // 11. check if there is already a database
                if (instance == null) {
                    // 12. use Room's "database builder" to build the database
                    instance = Room.databaseBuilder( // invoke dataBaseBuilder
                        context.applicationContext, // supply context
                        SleepDatabase::class.java, // tell it which database to build, passing in a reference to the SleepDatabase class
                        "sleep_history_database" // DB name
                    )
                        .fallbackToDestructiveMigration() // 13. migration step
                        .build() // 14. build the database
                    INSTANCE = instance // final step 15. assign INSTANCE to the newly created DB

                    // ***************** NOTES ON MIGRATION ***************** (step 13.
                    // i.e. if we change the database schema by changing the number or type columns
                    // for example.. we need a way to convert the existing tables and data into the new schema
                    // a migration object is an object that defines how you take all the rows of the old schema
                    // and convert them to rows in the new schema
                    // so if the user upgrades from a version of our app with one database schema, to a newer
                    // version of the app with a newer schema... their data is not lost
                    // this app simply wipes and rebuild the db, instead of migrating

                }

                // 10. return the instance
                return instance
            }
        }
    }
}










// inside the synchronized block:
//      Check whether the database already exists,
//          and if it does not, use Room.databaseBuilder to create it.