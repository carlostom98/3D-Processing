package com.alisys.androidar

import android.app.Application
import android.widget.Toast
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Session
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException
import dagger.hilt.android.HiltAndroidApp


class App: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}