package com.example.photogallery.api

import retrofit2.http.GET
import retrofit2.Call

interface FlickerApi {

    @GET("/")
    fun fetchContents():Call<String>

}