package ru.study.photogalleryv3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PhotoAdapter(var photos: List<Photo>) : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        return PhotoHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo) {
            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            Picasso.get()
                .load(photo.url)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(imageView)
            itemView.findViewById<TextView>(R.id.textView).text = photo.caption
        }
    }
}