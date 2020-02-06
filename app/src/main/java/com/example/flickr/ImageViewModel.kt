package com.example.flickr

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickr.Data.ImageDetails
import com.example.flickr.Data.Photo
import com.example.flickr.Network.CallBack

class ImageViewModel :ViewModel() {
    val imageRepository = ImageRepository()
    var livePhotoUrls = MutableLiveData<String>()
    var photoList = ArrayList<Photo>()
    var photoUrls = ArrayList<String>()
    lateinit var imageDetailsList: ImageDetails
    fun getImageDetails(id: String) {

        imageRepository.getImageDetails(object : CallBack<ImageDetails> {
            override fun onError() {
                Log.e("EVM", "onError")
            }

            override fun onSuccess(list: ImageDetails?) {
                list?.let {

                    imageDetailsList = (list)
                    photoList.clear()
                    photoList.addAll(imageDetailsList.photos.photo)
                    photoUrls.clear()
                    for (element in photoList) {
                        var temp="https://farm" + element.farm + ".staticflickr.com/"+ element.server +
                                "/" + element.id + "_" + element.secret + "_m.jpg"
                        photoUrls.add(temp)
                        livePhotoUrls.postValue(temp)
                    }
                }
            }
        }, id)
    }
}