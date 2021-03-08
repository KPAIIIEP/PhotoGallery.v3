package ru.study.photogalleryv3.di

import dagger.Component
import ru.study.photogalleryv3.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}