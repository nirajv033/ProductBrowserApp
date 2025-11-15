import Model.Product

sealed class Screen {
    object ProductList : Screen()
    data class ProductDetail(val product: Product) : Screen()
}