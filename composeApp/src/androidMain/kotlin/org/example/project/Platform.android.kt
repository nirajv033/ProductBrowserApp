package org.example.project

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
actual fun AppBackHandler(enabled: Boolean, onBack: () -> Unit) {
    BackHandler(enabled = enabled) { onBack() }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun PerformExitApp() {
    val activity = LocalContext.current as? Activity
    activity?.finish()
}

actual fun exitApp() {
    // iOS does not allow programmatically closing an app.
    // We simply do nothing.
}

@Composable
fun HandleBackPress(onBack: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                // You may customize behavior if needed
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    BackPressHandler(onBack)
}

@Composable
actual fun BackPressHandler(onBack: () -> Unit) {
    BackHandler { onBack() }
}