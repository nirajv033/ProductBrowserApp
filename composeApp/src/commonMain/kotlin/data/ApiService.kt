package data

import DTO.ProductsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ApiService(private val client: HttpClient) {
    suspend fun getProducts(limit: Int = 30, skip: Int = 0): ProductsResponse {
        return client.get("https://dummyjson.com/products?limit=$limit&skip=$skip").body()
    }

    suspend fun searchProducts(query: String, limit: Int = 30): ProductsResponse {
        return client.get("https://dummyjson.com/products/search?q=${query}&limit=$limit").body()
    }

    suspend fun getProductsByCategory(category: String, limit: Int = 30): ProductsResponse {
        return client.get("https://dummyjson.com/products/category/${category}?limit=$limit").body()
    }

    companion object {
        fun createDefault() = ApiService(HttpClientFactory.createClient())
    }

}