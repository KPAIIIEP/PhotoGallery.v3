package ru.study.photogalleryv3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.study.photogalleryv3.databinding.ItemViewBinding
import ru.study.photogalleryv3.di.PicassoService
import ru.study.photogalleryv3.model.Photo
import javax.inject.Inject

class PhotoAdapter @Inject constructor() : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

    @Inject lateinit var picassoService: PicassoService
    private lateinit var binding: ItemViewBinding
    private val photos: MutableList<Photo> = mutableListOf()

    fun update(photos: List<Photo>) {
        this.photos.clear()
        this.photos.addAll(photos)
        notifyDataSetChanged()
    }

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

    inner class PhotoHolder constructor(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            picassoService.loadImage(photo.url, binding.imageView)
            binding.textView.text = photo.caption
        }
    }
}