package org.example.project

import data.ApiService
import kotlin.test.*
import kotlinx.coroutines.test.runTest
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*
import kotlinx.serialization.Serializable
import repository.ProductRepositoryImpl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


class ProductRepositoryImplTest {

    // Helper function to create mock ApiService
    private fun mockApi(json: String): ApiService {
        val mockEngine = MockEngine { request ->
            respond(
                content = json,
                status = HttpStatusCode.OK,
                headers = headersOf("Content-Type", "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        return ApiService(client)
    }

    @Test
    fun testGetProducts() = runTest {
        val json = """
        {
          "products": [
            { "id": 1, "title": "Test Product", "description": "Desc", "category": "cat", "price": 1.0,
              "discountPercentage": 0.0, "rating": 5.0, "stock": 10, "tags": ["tag"],
              "brand": "Brand", "sku": "SKU", "weight": 1, "dimensions": {"width":1,"height":1,"depth":1},
              "warrantyInformation":"", "shippingInformation":"", "availabilityStatus":"", "reviews":[],
              "returnPolicy":"", "minimumOrderQuantity":1, "meta": {"createdAt":"", "updatedAt":"", "barcode":"", "qrCode":""},
              "thumbnail":"", "images":[] }
          ],
          "total": 1, "skip":0, "limit":1
        }
        """.trimIndent()

        val api = mockApi(json)
        val repo = ProductRepositoryImpl(api)

        val result = repo.getProducts()
        assertEquals(1, result.size)
        assertEquals("Test Product", result[0].title)
    }

    @Test
    fun testSearchProducts() = runTest {
        val json = """
        {
          "products": [
            { "id": 2, "title": "Search Product", "description": "Desc", "category": "cat", "price": 2.0,
              "discountPercentage": 0.0, "rating": 4.5, "stock": 5, "tags": ["tag"],
              "brand": "Brand", "sku": "SKU2", "weight": 1, "dimensions": {"width":1,"height":1,"depth":1},
              "warrantyInformation":"", "shippingInformation":"", "availabilityStatus":"", "reviews":[],
              "returnPolicy":"", "minimumOrderQuantity":1, "meta": {"createdAt":"", "updatedAt":"", "barcode":"", "qrCode":""},
              "thumbnail":"", "images":[] }
          ],
          "total": 1, "skip":0, "limit":1
        }
        """.trimIndent()

        val api = mockApi(json)
        val repo = ProductRepositoryImpl(api)

        val result = repo.searchProducts("query")
        assertEquals(1, result.size)
        assertEquals("Search Product", result[0].title)
    }

    @Test
    fun testGetProductsByCategory() = runTest {
        val json = """
        {
          "products": [
            { "id": 3, "title": "Category Product", "description": "Desc", "category": "beauty", "price": 3.0,
              "discountPercentage": 0.0, "rating": 4.0, "stock": 2, "tags": ["tag"],
              "brand": "Brand", "sku": "SKU3", "weight": 1, "dimensions": {"width":1,"height":1,"depth":1},
              "warrantyInformation":"", "shippingInformation":"", "availabilityStatus":"", "reviews":[],
              "returnPolicy":"", "minimumOrderQuantity":1, "meta": {"createdAt":"", "updatedAt":"", "barcode":"", "qrCode":""},
              "thumbnail":"", "images":[] }
          ],
          "total": 1, "skip":0, "limit":1
        }
        """.trimIndent()

        val api = mockApi(json)
        val repo = ProductRepositoryImpl(api)

        val result = repo.getProductsByCategory("beauty")
        assertEquals(1, result.size)
        assertEquals("Category Product", result[0].title)
    }
}