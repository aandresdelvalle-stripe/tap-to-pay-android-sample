package com.example.app

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app.components.ActionButton
import com.example.app.components.ButtonType
import com.example.app.components.KeyPad
import com.example.app.components.LoadingSpinner
import com.example.app.models.KeypadInput
import com.example.app.ui.theme.StripeTerminalAppsOnDevicesTheme
import com.example.app.viewmodel.CheckoutViewModel

@Preview(heightDp = 640, widthDp = 360)
@Composable
fun CheckoutScreenPreview() {
    StripeTerminalAppsOnDevicesTheme {
        CheckoutScreen(navController = rememberNavController())
    }
}

@Composable
fun BackIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.ArrowBackIos,
            contentDescription = "back",
            modifier = Modifier.size(34.dp),
            tint = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun AmountInput(amount: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Text(stringResource(id = R.string.keypad_prompt), modifier = modifier, fontSize = 32.sp)
        Text(amount, modifier = modifier, fontSize = 48.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CheckoutScreen(
    navController: NavController, viewModel: CheckoutViewModel = viewModel()
) {
    if (viewModel.paymentState.paymentIntent != null) {
        LaunchedEffect(viewModel.paymentState) {
            navController.navigate("payment_complete")
        }
    }
    Surface(
        color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()
    ) {
        if (viewModel.paymentState.isLoading || viewModel.paymentState.paymentIntent != null) {
            LoadingSpinner()
        } else {
            Column(
//      TODO: Does the padding belong here?
                modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.SpaceBetween
            ) {
                BackIcon(onClick = {
                    navController.navigate("home") {
                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                })
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    AmountInput(viewModel.amount)
                    Spacer(modifier = Modifier.height(48.dp))
                    KeyPad(hasValues = viewModel.amountInCents > 0,
                        onPressNum = { num -> viewModel.onInput(KeypadInput.Number(num)) },
                        onClear = { viewModel.onInput(KeypadInput.Clear) },
                        onDelete = { viewModel.onInput(KeypadInput.Delete) })
                }
                ActionButton(stringResource(id = R.string.payment_button),
                    buttonType = ButtonType.Primary,
                    isEnabled = !viewModel.paymentState.isLoading && viewModel.amountInCents >= 100,
                    onClick = {
                        viewModel.makePayment(onFailure = {})
                    })
            }
        }
    }
}
