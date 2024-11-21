package com.simformsolutions.oneloop.initializer

import android.content.Context
import androidx.startup.Initializer
import com.simformsolutions.oneloop.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("TimberInitializer is initialized")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}