package com.example.app.network

import com.example.app.models.ConnectionToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BackendService {

    @POST("connection-token")
    fun getConnectionToken(): Call<ConnectionToken>

    @FormUrlEncoded
    @POST("capture-payment-intent")
    fun capturePaymentIntent(@Field("payment_intent_id") id: String): Call<Void>
}