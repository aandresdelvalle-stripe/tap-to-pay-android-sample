package com.example.app.components

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.app.TerminalListener
import com.example.app.network.TokenProvider
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.stripe.stripeterminal.Terminal
import com.stripe.stripeterminal.log.LogLevel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestAppPermissions(multiplePermissionsState: MultiplePermissionsState) {
    // Check permissions whenever the app starts
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                multiplePermissionsState.launchMultiplePermissionRequest()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        multiplePermissionsState.permissions.forEach { permission ->
            when (permission.permission) {
                Manifest.permission.ACCESS_FINE_LOCATION -> {
                    when {
                        permission.hasPermission -> {

                        }
                        permission.shouldShowRationale -> {
                            Text("Access fine location is needed for using Terminal")
                        }
                        !permission.hasPermission && !permission.shouldShowRationale -> {
                            Text("Enable location in the app settings")
                        }
                    }
                }
                Manifest.permission.BLUETOOTH -> {
                    when {
                        permission.hasPermission -> {

                        }
                        permission.shouldShowRationale -> {
                            Text("Bluetooth permission is needed for using Terminal")
                        }
                        !permission.hasPermission && !permission.shouldShowRationale -> {
                            Text("Enable Bluetooth in the app settings")
                        }
                    }
                }
            }
        }
    }
}

