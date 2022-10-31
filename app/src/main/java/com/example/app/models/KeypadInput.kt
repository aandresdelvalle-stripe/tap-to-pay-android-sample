package com.example.app.models

sealed class KeypadInput {
    data class Number(val number: Int) : KeypadInput()
    object Delete : KeypadInput()
    object Clear : KeypadInput()
}