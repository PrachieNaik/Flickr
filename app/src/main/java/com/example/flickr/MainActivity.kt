package com.example.flickr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var imageViewModel:ImageViewModel
    private val adapter = ListViewAdapter{ item : String -> itemClicked(item) }
    val layoutManager=LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        eventsRecyclerView.layoutManager=layoutManager
        eventsRecyclerView.adapter = adapter
        imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        adapter.updateData(imageViewModel.photoUrls)
        addScrollListener()
        imageViewModel.livePhotoUrls.observe(this, Observer {

            adapter.updateList(it)
        })
        addMoreElements()
    }
    private fun itemClicked(item : String) {
        Toast.makeText(this, "Clicked:$item ", Toast.LENGTH_LONG).show()
        val showDetailActivityIntent = Intent(this, DisplayImage::class.java)
        showDetailActivityIntent.putExtra(Intent.EXTRA_TEXT, item)
        startActivity(showDetailActivityIntent)
    }
    var mIsLoading = false
    private fun addScrollListener() {
        val mScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (mIsLoading)
                    return
                val visibleItemCount = layoutManager!!.childCount
                val totalItemCount = layoutManager!!.itemCount
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
        var temp: Int? =imageViewModel.pageChange.value
        if(temp==null)
            imageViewModel.pageChange.postValue(1)
        else
        imageViewModel.pageChange.postValue(temp + 1)
        Log.e("PageChange","${imageViewModel.pageChange.value}")
    }
}
