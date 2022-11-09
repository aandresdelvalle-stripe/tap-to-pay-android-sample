package com.example.app.helpers

import java.text.NumberFormat
import java.util.*

fun formatAmount(numStr: String): String {
    var numCents = numStr.toFloat() / 100f
    return NumberFormat.getCurrencyInstance(Locale("en", Vars.CountryCode)).format(numCents)
}