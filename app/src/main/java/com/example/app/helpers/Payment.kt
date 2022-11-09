package com.example.app.helpers

import com.example.app.network.ApiClient
import com.stripe.stripeterminal.Terminal
import com.stripe.stripeterminal.external.callable.PaymentIntentCallback
import com.stripe.stripeterminal.external.models.PaymentIntent
import com.stripe.stripeterminal.external.models.PaymentIntentParameters
import com.stripe.stripeterminal.external.models.TerminalException

class Payment {
    fun collectPayment(
        amount: Long,
        onFailure: (e: TerminalException) -> Unit,
        onSuccess: (paymentIntent: PaymentIntent) -> Unit
    ) {
        _createPayment(
            amount,
            onSuccess = { paymentIntent ->
                _collectPayment(
                    paymentIntent,
                    onSuccess = onSuccess,
                    onFailure = onFailure
                )
            }, onFailure = onFailure
        )
    }

    private fun _createPayment(
        amount: Long,
        onSuccess: (paymentIntent: PaymentIntent) -> Unit,
        onFailure: (e: TerminalException) -> Unit
    ) {
        val paymentIntentParams =
            PaymentIntentParameters.Builder().setAmount(amount).setCurrency(Vars.Currency)
                .build()

        val paymentIntentCallBack = object : PaymentIntentCallback {
            override fun onFailure(e: TerminalException) {
                onFailure(e)
            }

            override fun onSuccess(paymentIntent: PaymentIntent) {
                onSuccess(paymentIntent)
            }
        }
        val term = Terminal.getInstance();
        term.createPaymentIntent(paymentIntentParams, paymentIntentCallBack)
    }

    private fun _collectPayment(
        paymentIntent: PaymentIntent,
        onSuccess: (paymentIntent: PaymentIntent) -> Unit,
        onFailure: (e: TerminalException) -> Unit,
    ) {

        val processPaymentCallback by lazy {
            object : PaymentIntentCallback {
                override fun onSuccess(paymentIntent: PaymentIntent) {
                    ApiClient.capturePaymentIntent(paymentIntent.id)
                    onSuccess(paymentIntent)
                }

                override fun onFailure(e: TerminalException) {
                    onFailure(e)
                }
            }
        }

        val collectPaymentMethodCallback by lazy {
            object : PaymentIntentCallback {
                override fun onSuccess(paymentIntent: PaymentIntent) {
                    Terminal.getInstance().processPayment(paymentIntent, processPaymentCallback)
                }

                override fun onFailure(e: TerminalException) {
                    onFailure(e)
                }
            }
        }
        Terminal.getInstance().collectPaymentMethod(paymentIntent, collectPaymentMethodCallback)
    }

}


