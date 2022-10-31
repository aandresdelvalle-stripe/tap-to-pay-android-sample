package com.example.app.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

sealed class ButtonType {
    object Primary : ButtonType()
    object Secondary : ButtonType()
}


@Composable
fun ActionButton(content: String, buttonType: ButtonType, isEnabled: Boolean = true, onClick: () -> Unit) {

    val defaultButtonColors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onSecondary,
    )

    var buttonColors: ButtonColors = when (buttonType) {
        ButtonType.Primary -> ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            disabledBackgroundColor = MaterialTheme.colors.secondary,
            disabledContentColor = MaterialTheme.colors.onSecondary
        )
        ButtonType.Secondary ->  defaultButtonColors
    }

    Button(
        colors = buttonColors,
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        onClick = onClick,
        enabled = isEnabled
    ) {
        Text(content)
    }
}