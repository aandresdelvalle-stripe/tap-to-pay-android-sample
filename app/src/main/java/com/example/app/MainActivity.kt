package com.example.app

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.app.components.RequestAppPermissions
import com.example.app.network.TokenProvider
import com.example.app.ui.theme.StripeTerminalAppsOnDevicesTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.stripe.stripeterminal.Terminal
import com.stripe.stripeterminal.log.LogLevel

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StripeTerminalAppsOnDevicesTheme {
                val permissions = if (Build.VERSION.SDK_INT >= 31) {
                    listOf(
                        Manifest.permission.BLUETOOTH,
                        Manifest.permission.BLUETOOTH_ADMIN,
                        Manifest.permission.BLUETOOTH_CONNECT,
                        Manifest.permission.BLUETOOTH_SCAN,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                } else {
                    listOf(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                }
                val multiplePermissionsState = rememberMultiplePermissionsState(
                    permissions = permissions
                )
                RequestAppPermissions(multiplePermissionsState)
                initTerminal(applicationContext)
                if (multiplePermissionsState.allPermissionsGranted && Terminal.isInitialized()) {
                    Navigation()
                }
            }
        }
    }
}

fun initTerminal(
    context: Context,
    logLevel: LogLevel = LogLevel.VERBOSE,
) {
    if (Terminal.isInitialized()) return
    try {
        Terminal.initTerminal(
            context,
            logLevel,
            TokenProvider(),
            TerminalListener()
        )
        Log.d("initTerminal", "Terminal initialized")
    } catch (e: Exception) {
        Log.d("initTerminal", "Initializing Terminal failed: ${e.message}")
    }
}