package com.example.flickr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var imageViewModel:ImageViewModel
    val adapter = ListViewAdapter()
    val layoutManager=LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        eventsRecyclerView.layoutManager=layoutManager
        eventsRecyclerView.adapter = adapter
        addScrollListener()
        imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        imageViewModel.livePhotoUrls.observe(this, Observer {
            adapter.updateList(imageViewModel.photoUrls)
        })
        imageViewModel.getImageDetails("1")

    }

    var mIsLoading = false
    private fun addScrollListener() {
        val mScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (mIsLoading)
                    return
                val visibleItemCount = layoutManager.getChildCount()
                val totalItemCount = layoutManager.getItemCount()
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    mIsLoading=true
                    Handler().postDelayed( {addMoreElements()}, 1000)
                }
            }
        }
        eventsRecyclerView.addOnScrollListener(mScrollListener)
    }



    private fun addMoreElements(){
        mIsLoading=false
        val temp=imageViewModel.imageDetailsList.photos.page
        imageViewModel.getImageDetails((temp+1).toString())

    }


}
