package com.example.flickr.Network

import android.util.Log
import com.example.flickr.Data.ImageDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ImageManager{

    var retrofit: Retrofit
    var fetchApi: FetchApi

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(FetchApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        fetchApi = retrofit.create(FetchApi::class.java)
        Log.e("IM","init")
    }
    fun getImageData(callBack: CallBack<ImageDetails>,id:String){
        var url="?format=json&nojsoncallback=1&api_key=04a42d236e746206fbbf64245342dd2d&method=flickr.photos.search&tags=mode&per_page=10&page="+id
        val call: Call<ImageDetails> = fetchApi.getImageDetailsList(url)
        call.enqueue(object: Callback<ImageDetails>       //will be executed on different code
        {
            override fun onFailure(call: Call<ImageDetails>?, t: Throwable?) {
                t?.printStackTrace()
                Log.e("IM","onFailure")
            }
            override fun onResponse(call: Call<ImageDetails>?, response: Response<ImageDetails>?) {
                if(!response!!.isSuccessful)
                {
                    Log.e("EventManager",response.toString())
                }
                callBack.onSuccess(response.body())

            }

        })

    }

}
