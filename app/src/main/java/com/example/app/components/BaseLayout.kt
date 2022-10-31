package com.example.app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenLayout(
    cornerIcon: @Composable() ()-> Unit?,
    mainContent: @Composable() ()-> Unit,
    actionButton: @Composable() () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {
            cornerIcon()
            mainContent()
            actionButton()
        }
    }

}