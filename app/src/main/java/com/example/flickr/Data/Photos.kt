package com.example.flickr.Data

import com.example.flickr.Data.Photo

data class Photos (
    var page: Int,
    var pages: Int,
    var perpage: Int,
    var total: Int,
    var photo: List<Photo>
)