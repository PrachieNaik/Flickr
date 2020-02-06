package com.example.flickr.Data

data class Photo (
    var id: String,
    var owner: String,
    var secret: String,
    var server: String,
    var farm: Int,
    var title: String
)