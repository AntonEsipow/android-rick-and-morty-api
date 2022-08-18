package com.bigtoapp.simplerick

import android.app.Application
import android.content.Context

class SimpleRickApplication: Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}