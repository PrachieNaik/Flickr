package com.example.flickr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display_image.*

class DisplayImage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_image)

        val intent= intent
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            var url = intent.getStringExtra(Intent.EXTRA_TEXT)
            Picasso.get().load(url).into(fullImage)
        }

    }

}
