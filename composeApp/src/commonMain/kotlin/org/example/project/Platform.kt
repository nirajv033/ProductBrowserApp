package org.example.project

import androidx.compose.runtime.Composable

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun AppBackHandler(enabled: Boolean = true, onBack: () -> Unit)


expect fun exitApp()

@Composable
expect fun BackPressHandler(onBack: () -> Unit)