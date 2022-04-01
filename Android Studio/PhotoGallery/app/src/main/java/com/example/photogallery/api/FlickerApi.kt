package com.example.photogallery.api

import retrofit2.http.GET
import retrofit2.Call

interface FlickerApi {

//    @GET("/")
//    fun fetchContents():Call<String>

    @GET("services/rest/?method=flickr.interestingness.getList" +
            "&api_key=API_KEY" +
            "&format=json" +
            "&nojsoncallback=1" + "&extras=url_s")
    fun fetchPhotos():Call<String>

}