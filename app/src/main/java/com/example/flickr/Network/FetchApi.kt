package com.example.flickr.Network

import com.example.flickr.Data.ImageDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface FetchApi {
    companion object {
        val BASE_URL = "https://api.flickr.com/services/rest/"
    }
    @GET
    fun getImageDetailsList(@Url url:String): Call<ImageDetails>

}