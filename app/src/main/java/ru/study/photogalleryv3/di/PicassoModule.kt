package ru.study.photogalleryv3.di

import android.widget.ImageView
import com.squareup.picasso.Picasso
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

interface PicassoService {
    fun loadImage(url: String, view: ImageView)
}

class PicassoServiceImpl @Inject constructor() : PicassoService {
    override fun loadImage(url: String, view: ImageView) {
        Picasso.get()
            .load(url)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(view)
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class PicassoModule {

    @Binds
    abstract fun bindPicassoService(picassoServiceImpl: PicassoServiceImpl): PicassoService
}