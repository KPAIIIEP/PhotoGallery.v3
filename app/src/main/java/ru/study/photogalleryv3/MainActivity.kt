package ru.study.photogalleryv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this)
        val photoAdapter = PhotoAdapter(mutableListOf())
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = linearLayoutManager
            adapter = photoAdapter
        }

        FlickrApi.flickrApi.getPhotos().enqueue(object : Callback<FlickrPhotosResponse> {
            override fun onResponse(call: Call<FlickrPhotosResponse>, response: Response<FlickrPhotosResponse>) {
                val result = response.body()
                result?.let {
                    photoAdapter.photos = it.response.photos
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<FlickrPhotosResponse>, t: Throwable) {

            }
        })
    }
}