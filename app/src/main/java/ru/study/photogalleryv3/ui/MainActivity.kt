package ru.study.photogalleryv3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.study.photogalleryv3.App
import ru.study.photogalleryv3.network.FlickrApi
import ru.study.photogalleryv3.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var recyclerView: RecyclerView
    @Inject
    lateinit var flickrApi: FlickrApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).appComponent.inject(this)

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
        flickrApi.getPhotos()
                .map { result -> result?.let { it.response.photos } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { photos ->
                    photoAdapter.photos = photos
                    recyclerView.adapter?.notifyDataSetChanged()
                }
    }
}