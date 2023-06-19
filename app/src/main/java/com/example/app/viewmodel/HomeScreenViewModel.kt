package com.example.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.app.helpers.Vars
import com.stripe.stripeterminal.Terminal
import com.stripe.stripeterminal.external.callable.*
import com.stripe.stripeterminal.external.models.*

class HomeScreenViewModel : ViewModel() {
    private var discoveryCancelable: Cancelable? = null

    //TODO: CHANGEME - UK
    companion object {
        private const val LOCATION: String = Vars.LocationID;
    }

    fun startDiscovery(
        onFailure: (e: TerminalException) -> Unit,
        onSuccess: () -> Unit
    ) {
        if (discoveryCancelable == null && Terminal.getInstance().connectedReader == null) {
            val discoveryConfig = DiscoveryConfiguration(
                timeout = 0,
                discoveryMethod = DiscoveryMethod.LOCAL_MOBILE,
                isSimulated = false,
                location = LOCATION
            )
            val discoveryListener = object : DiscoveryListener {
                override fun onUpdateDiscoveredReaders(readers: List<Reader>) {
                    connectReader(readers.first())
                }
            }
            val discoveryCallback = object : Callback {
                override fun onFailure(e: TerminalException) {
                    onFailure(e)
                }

                override fun onSuccess() {
                    onSuccess()
                }
            }
            discoveryCancelable = Terminal.getInstance()
                .discoverReaders(discoveryConfig, discoveryListener, discoveryCallback)
        }
    }

    private fun connectReader(reader: Reader) {
        val config = ConnectionConfiguration.LocalMobileConnectionConfiguration(LOCATION)
        val readerCallback = object : ReaderCallback {
            override fun onFailure(e: TerminalException) {
                TODO("Not yet implemented")
                Log.d("StripeTerminal-Reader", "Reader NOT CONNECTED")

            }

            override fun onSuccess(reader: Reader) {
                Log.d("StripeTerminal-Reader", "Reader connected")
            }
        }
        Terminal.getInstance().connectLocalMobileReader(reader, config,  readerCallback)
    }
}