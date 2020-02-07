package com.example.flickr

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.flickr.Data.ImageDetails
import com.example.flickr.Data.Photo
import com.example.flickr.Network.CallBack

class ImageViewModel :ViewModel() {
    private val imageRepository = ImageRepository()
    private var livePhotoUrls = MutableLiveData<List<Photo>>()
    var pageChange = MutableLiveData<Int>()
    var list=ArrayList<Photo>()
    var data = Transformations.switchMap(pageChange) {
        imageRepository.getImageDetails(object : CallBack<ImageDetails> {
            override fun onError() {
                Log.e("EVM", "onError")
            }

            override fun onSuccess(list: ImageDetails?) {
                list?.let {
                    list.photos.photo.let {
                        livePhotoUrls.postValue(it)
                    }
                    Log.e("livephotourls","${livePhotoUrls.value}")
                }
            }
        }, it.toString())

        livePhotoUrls
    }


}