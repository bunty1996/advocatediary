package com.advocatediary.utils

import android.app.Application
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

open class ApplicationClass : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        WebServices().create()
        MultiDex.install(applicationContext)
        // ApplicationComponent is our component interface.
        // NetModule is our Module class.

    }
}