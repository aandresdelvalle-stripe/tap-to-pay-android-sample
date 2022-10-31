package com.example.app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.StripeTerminalAppsOnDevicesTheme

@Preview
@Composable
fun SpinnerPreview() {
    StripeTerminalAppsOnDevicesTheme {
        Surface {
            LoadingSpinner()
        }
    }
}


@Composable
fun LoadingSpinner() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(
            modifier = Modifier.size(160.dp),
            color = MaterialTheme.colors.primary,
            strokeWidth = 8.dp
        )
    }
}