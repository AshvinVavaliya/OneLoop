package com.simformsolutions.oneloop.initializer

import android.content.Context
import androidx.startup.Initializer
import com.simform.bagel.Bagel
import com.simformsolutions.oneloop.BuildConfig

class BagelInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) { // Only expose in debug
            Bagel.start(context)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> =
        mutableListOf()
}