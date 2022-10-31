package com.example.app

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.runtime.Composable
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
import com.example.app.ui.theme.StripeTerminalAppsOnDevicesTheme


@Preview(heightDp = 640, widthDp = 360)
@Composable
fun PaymentCompleteScreenPreview() {
    StripeTerminalAppsOnDevicesTheme(darkTheme = true) {
        val navController: NavController = rememberNavController()
        PaymentCompleteScreen(navController = navController)
    }
}


@Composable
fun PaymentCompleteScreen(navController: NavController) {
    Surface(
        color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()
    ) {
        Column(
//      TODO: Does the padding belong here?
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 56.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Receipt,
                    contentDescription = stringResource(id = R.string.receipt_description),
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    stringResource(id = R.string.payment_complete_title),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(stringResource(id = R.string.payment_complete_description), fontSize = 20.sp)
            }
            Column {
                Divider(thickness = 2.dp)
                Spacer(modifier = Modifier.height(16.dp))
                ActionButton(
                    stringResource(id = R.string.back_button),
                    buttonType = ButtonType.Secondary
                ) {
                    navController.navigate("home") {
                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}