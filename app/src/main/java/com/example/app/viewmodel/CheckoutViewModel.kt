package com.example.app.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.helpers.formatAmount
import com.example.app.helpers.Payment
import com.example.app.models.KeypadInput
import com.example.app.models.PaymentState
import com.stripe.stripeterminal.external.models.TerminalException
import kotlinx.coroutines.launch

class CheckoutViewModel() : ViewModel() {
    companion object {
        private const val MAX_INPUT_LENGTH = 10
    }
    private var _amount by mutableStateOf("")
    var amountInCents by mutableStateOf(0)
        private set
    var paymentState by mutableStateOf(PaymentState(false, null))
        private set
    var amount by mutableStateOf("")
        private set

    init {
        amount = formatAmount("0")
    }

    fun onInput(input: KeypadInput) {
        when (input) {
            is KeypadInput.Number -> addNumber(input.number)
            is KeypadInput.Clear -> clearNumbers()
            is KeypadInput.Delete -> deleteNumber()
        }
    }

    fun makePayment(onFailure: (e: TerminalException) -> Unit) {
        if (paymentState.isLoading) return
        paymentState = PaymentState(true, null)
        viewModelScope.launch {
            Payment().collectPayment(
                amountInCents.toLong(),
                onSuccess = { paymentIntent ->
                    paymentState = PaymentState(false, paymentIntent)
                    Log.d("test", "payment completed")
                },
                onFailure = { e ->
                    paymentState = PaymentState(false, null)
                    onFailure(e)
                }
            )
        }
    }

    private fun setAmountStates(_amount: String) {
        amount = formatAmount(_amount)
        amountInCents = _amount.toInt()
    }

    private fun addNumber(number: Int) {
        if (amount.length >= MAX_INPUT_LENGTH) {
            return
        }
        _amount = _amount.plus(number.toString())
        setAmountStates(_amount)
    }

    private fun clearNumbers() {
        if (_amount !== "0") {
            _amount = "0"
            setAmountStates(_amount)
        }
    }

    private fun deleteNumber() {
        if (_amount.isNotEmpty()) {
            _amount = when (_amount.length) {
                1 -> "0"
                else -> _amount.dropLast(1)
            }
            setAmountStates(_amount)
        }
    }
}



