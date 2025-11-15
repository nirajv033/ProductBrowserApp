package org.example.project

import Screen
import UI.ExitDialog
import UI.ProductDetailScreen
import UI.ProductListScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import presentation.ProductViewModel

@Composable
fun App(vm: ProductViewModel) {

    var currentScreen by remember { mutableStateOf<Screen>(Screen.ProductList) }

    when (val screen = currentScreen) {

        is Screen.ProductList -> {
            ProductListScreen(
                vm = vm,
                onProductClick = { clicked ->
                    currentScreen = Screen.ProductDetail(clicked)
                }
            )
        }

        is Screen.ProductDetail -> {
            AppBackHandler {
                currentScreen = Screen.ProductList
            }

            ProductDetailScreen(
                product = screen.product,
                onBack = {
                    currentScreen = Screen.ProductList
                }
            )
        }
    }
}