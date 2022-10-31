package com.example.app

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app.components.ActionButton
import com.example.app.components.ButtonType
import com.example.app.ui.theme.DarkAction
import com.example.app.viewmodel.HomeScreenViewModel

@Composable
fun StripeLogo() {
    if (isSystemInDarkTheme()) {
        Image(
            painterResource(id = R.drawable.stripe_logo_light),
            stringResource(id = R.string.stripe_logo_description),
        )
    } else {
        Image(
            painterResource(id = R.drawable.stripe_logo_dark),
            stringResource(id = R.string.stripe_logo_description),
        )
    }
}

@Composable
fun StripeLogoFragment() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StripeLogo()
        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(id = R.string.terminal_app_description))
    }
}

@Composable
fun SettingsIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "", // TODO: Fill this
            modifier = Modifier.size(32.dp),
            tint = DarkAction
        )
    }
}

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel = viewModel()) {
    viewModel.startDiscovery(
        onSuccess = { Log.d("HomeScreen", "Discovered reader") },
        onFailure = { "Didn't discover reader" })
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
//            TODO: Does the padding belong here?
            modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {
            SettingsIcon(onClick = { })
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 144.dp)
            ) {
                StripeLogoFragment()
            }
            ActionButton(
                stringResource(id = R.string.payment_button),
                buttonType = ButtonType.Primary,
                onClick = {
                    navController.navigate("checkout")
                }
            )

        }
    }
}