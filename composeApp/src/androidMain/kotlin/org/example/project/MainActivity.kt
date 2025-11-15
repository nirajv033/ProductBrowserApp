package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import data.ApiService
import domain.GetProductsUseCase
import domain.SearchProductsUseCase
import presentation.ProductViewModel
import repository.ProductRepositoryImpl

class MainActivity : ComponentActivity() {

    private val viewModel by lazy {
        val api = ApiService.createDefault()
        val repo = ProductRepositoryImpl(api)
        ProductViewModel(GetProductsUseCase(repo), SearchProductsUseCase(repo))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                App(viewModel)   // <-- IMPORTANT
            }
        }

        viewModel.loadProducts()
    }
}
