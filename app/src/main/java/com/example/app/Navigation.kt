package com.example.app

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController, startDestination = "home") {
        composable("home", enterTransition = {
            fadeIn() + slideInHorizontally(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                initialOffsetX = { 300 })
        }, exitTransition = {
            fadeOut(
                animationSpec = tween(100, easing = FastOutSlowInEasing),
            )
        }) {
            HomeScreen(navController)
        }
        composable("checkout", enterTransition = {
            fadeIn() + slideInHorizontally(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                initialOffsetX = { 300 })
        }, exitTransition = {
            fadeOut(
                animationSpec = tween(100, easing = FastOutSlowInEasing),
            )
        }) {
            CheckoutScreen(navController)
        }
        composable("payment_complete", enterTransition = {
            fadeIn() + slideInHorizontally(
                animationSpec = tween(300, easing = FastOutSlowInEasing),
                initialOffsetX = { 300 })
        }) {
            PaymentCompleteScreen(navController)
        }
    }
}