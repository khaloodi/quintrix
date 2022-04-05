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
 *
 */

package com.example.android.marsrealestate.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://mars.udacity.com/"
// this file contains the Network Layer, the API that the ViewModel uses to communicate w/web service

// todo Use Moshi Builder to create a Moshi object with the KotlinJsonAdapterFactory
//  for Moshi's annotations to work properly w/Kotlin
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// todo Use Retrofit.Builder to create the Retrofit object.
private val retrofit = Retrofit.Builder()
    // .addConverterFactory(ScalarsConverterFactory.create()) // lets retrofit now how to turn a json response into a string
    .addConverterFactory(MoshiConverterFactory.create(moshi))// todo Let retrofit know to convert the response into Kotlin objects
    .addCallAdapterFactory(CoroutineCallAdapterFactory()) // todo enable a coroutine based api, e.g. return something other than the default call class
    // this line may be deprecated ^^^/ unnecessary
    .baseUrl(BASE_URL)
    .build() // creates the retrofit object

// Create a MarsApiService interface, and define a getProperties() method to request the JSON response string
interface MarsApiService {
    @GET("realestate")
    suspend fun getProperties(): // added suspend keyword after adding coroutines
            // todo After adding Moshi, ask retrofit to return a list of mars property objects from json
            //  array, instead of returning a Json string
            // Call<String>
            // Call<List<MarsProperty>>
            List <MarsProperty>
}

// Passing in the service API you just defined, create a public object called MarsApi to expose
// the Retrofit service to the rest of the app:
object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java) // initialization
    }
}