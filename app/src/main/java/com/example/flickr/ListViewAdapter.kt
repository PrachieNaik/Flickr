package com.example.flickr
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.grid_view_item.view.*
import java.net.URI
import java.net.URL


class ListViewAdapter: RecyclerView.Adapter< ListViewAdapter.ItemVH>()  {
    val urls=ArrayList<String>()
    fun updateList(list:List<String>) {
        var temp=urls.size
        urls.addAll(list)
        notifyItemRangeChanged(temp,list.size)
    }
    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bindItems(urls[position])
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


        fun bindItems(item: String) {
            with(itemView)
            {
                val url: URI =  URL(item).toURI()
                Picasso.get().load(url.toString()).into(grid_item_image)
            }


        }
    }



}