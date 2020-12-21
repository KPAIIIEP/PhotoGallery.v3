package ru.study.photogalleryv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    lateinit var photoAdapter: PhotoAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this)
        photoAdapter = PhotoAdapter(mutableListOf())
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = linearLayoutManager
            adapter = photoAdapter
        }

        findViewById<Button>(R.id.button).apply {
            setOnClickListener {
                update()
            }
        }

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