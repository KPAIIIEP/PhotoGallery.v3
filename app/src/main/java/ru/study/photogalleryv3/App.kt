package ru.study.photogalleryv3

import android.app.Application
import ru.study.photogalleryv3.di.AppComponent
import ru.study.photogalleryv3.di.DaggerAppComponent

class App : Application() {

    var appComponent: AppComponent = DaggerAppComponent.builder().build()
        private set
}