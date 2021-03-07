package ru.study.photogalleryv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.study.photogalleryv3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var photoAdapter: PhotoAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linearLayoutManager = LinearLayoutManager(this)
        photoAdapter = PhotoAdapter(mutableListOf())
        recyclerView = binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = photoAdapter
        }

        binding.button.setOnClickListener { update() }

        update()
    }

    private fun update() {
        FlickrApi.flickrApi.getPhotos()
                .map { result -> result?.let { it.response.photos } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { photos ->
                    photoAdapter.photos = photos
                    recyclerView.adapter?.notifyDataSetChanged()
                }
    }
}