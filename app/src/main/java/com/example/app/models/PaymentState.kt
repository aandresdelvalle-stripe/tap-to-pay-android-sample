package com.example.app.models

import com.stripe.stripeterminal.external.models.PaymentIntent

data class PaymentState (
    val isLoading: Boolean = false,
    val paymentIntent: PaymentIntent? = null
)