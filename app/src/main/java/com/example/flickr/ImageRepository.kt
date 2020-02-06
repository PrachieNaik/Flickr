package com.example.flickr

import android.util.Log
import com.example.flickr.Data.ImageDetails
import com.example.flickr.Network.CallBack
import com.example.flickr.Network.ImageManager

class ImageRepository {

    var imageManager= ImageManager

    fun getImageDetails(callback: CallBack<ImageDetails>,id:String){
        Log.e("Repo","getImageDetails")
        imageManager.getImageData(callback,id)

    }
}