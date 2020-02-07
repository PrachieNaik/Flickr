package com.example.flickr
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.Data.Photo
import kotlinx.android.synthetic.main.grid_view_item.view.*

class ListViewAdapter(private val clickListener: (String) -> Unit): RecyclerView.Adapter< ListViewAdapter.ItemVH>()  {
    val urls=ArrayList<Photo>()
    fun updateList(list:List<Photo>) {
        var temp=urls.size
        urls.addAll(list)
        Log.e("urls","$urls")
        notifyItemRangeChanged(temp,list.size)
    }
    fun updateData(list:List<Photo>) {
        var temp=urls.size
        urls.addAll(list)
        Log.e("urls","$urls")
       // notifyItemRangeChanged(temp,list.size)
    }
    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bindItems(urls[position],clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_view_item, parent, false)
        return ItemVH(v)
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    class ItemVH(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(item: Photo,clickListener: (String) -> Unit)
        {
            with(itemView)
            {
                Picasso.get().load(item.getUrl()).into(grid_item_image)
                itemView.setOnClickListener { clickListener(item.getUrl())}
            }
        }
    }



}