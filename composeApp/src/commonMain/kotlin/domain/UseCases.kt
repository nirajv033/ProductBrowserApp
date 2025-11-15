package domain

import Model.Product
import repository.ProductRepository


class GetProductsUseCase(private val repo: ProductRepository) {
    suspend operator fun invoke(): List<Product> = repo.getProducts()
}

class SearchProductsUseCase(private val repo: ProductRepository) {
    suspend operator fun invoke(query: String): List<Product> = repo.searchProducts(query)
}

class GetProductsByCategoryUseCase(private val repo: ProductRepository) {
    suspend operator fun invoke(category: String): List<Product> = repo.getProductsByCategory(category)
}