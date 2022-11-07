package com.thurainx.libraryapp

import android.app.Application
import com.thurainx.libraryapp.data.models.LibraryModelImpl

class TheLibraryApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        LibraryModelImpl.initDatabase(context = applicationContext)
    }
}