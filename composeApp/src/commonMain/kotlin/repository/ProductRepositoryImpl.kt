package repository

import Model.Product
import data.ApiService

class ProductRepositoryImpl(private val api: ApiService): ProductRepository {
    override suspend fun getProducts(): List<Product> = api.getProducts().products
    override suspend fun searchProducts(query: String): List<Product> = api.searchProducts(query).products
    override suspend fun getProductsByCategory(category: String): List<Product> = api.getProductsByCategory(category).products
}