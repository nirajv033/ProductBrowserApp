package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import presentation.ProductViewModel
import domain.GetProductsUseCase
import domain.SearchProductsUseCase
import data.ApiService
import repository.ProductRepositoryImpl

fun MainViewController(viewModel: ProductViewModel) =
    ComposeUIViewController {
        App(viewModel)
    }

