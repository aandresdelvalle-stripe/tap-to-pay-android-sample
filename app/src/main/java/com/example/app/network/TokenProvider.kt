package com.example.app.network

import com.stripe.stripeterminal.external.callable.ConnectionTokenCallback
import com.stripe.stripeterminal.external.callable.ConnectionTokenProvider
import com.stripe.stripeterminal.external.models.ConnectionTokenException

class TokenProvider : ConnectionTokenProvider {
    override fun fetchConnectionToken(callback: ConnectionTokenCallback) {
        try {
            val token = ApiClient.createConnectionToken()
            callback.onSuccess(token)
        } catch (e: ConnectionTokenException) {
            callback.onFailure(e)
        }
    }
}