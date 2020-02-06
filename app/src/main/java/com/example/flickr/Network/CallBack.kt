package com.example.flickr.Network

interface CallBack<T> {
    fun onSuccess(list: T?)
    fun onError()
}