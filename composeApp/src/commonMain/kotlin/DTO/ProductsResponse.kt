package DTO

import Model.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    val products: List<Product>,
    val total: Int = 0,
    val skip: Int = 0,
    val limit: Int = 0
)