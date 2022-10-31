package com.example.app

import android.app.Application
import android.os.StrictMode
import com.stripe.stripeterminal.TerminalApplicationDelegate


class StripeTerminalApplication : Application() {

    override fun onCreate() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectAll()
                .penaltyLog().build()
        )
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().build()
        )
        super.onCreate()
        TerminalApplicationDelegate.onCreate(this)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        TerminalApplicationDelegate.onTrimMemory(this, level)
    }
}