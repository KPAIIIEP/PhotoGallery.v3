package ru.study.photogalleryv3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.study.photogalleryv3.databinding.ItemViewBinding

class PhotoAdapter(var photos: List<Photo>) : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

    private lateinit var binding: ItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    class PhotoHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            Picasso.get()
                .load(photo.url)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(binding.imageView)
            binding.textView.text = photo.caption
        }
    }
}