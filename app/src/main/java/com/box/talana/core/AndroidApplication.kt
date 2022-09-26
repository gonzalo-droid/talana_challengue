package com.box.talana.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}