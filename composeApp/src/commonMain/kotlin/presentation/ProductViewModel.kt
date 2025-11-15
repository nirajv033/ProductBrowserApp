package presentation

import Model.Product
import domain.GetProductsUseCase
import domain.SearchProductsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProductUiState(
    val loading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)

class ProductViewModel(
    private val getProducts: GetProductsUseCase,
    private val searchProducts: SearchProductsUseCase
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _state = MutableStateFlow(ProductUiState())
    val state: StateFlow<ProductUiState> = _state.asStateFlow()

    fun loadProducts() {
        _state.value = ProductUiState(loading = true)
        scope.launch {
            try {
                val items = getProducts()
                _state.value = ProductUiState(products = items)
            } catch (t: Throwable) {
                _state.value = ProductUiState(error = t.message ?: "Unknown")
            }
        }
    }

    fun search(query: String) {
        _state.value = ProductUiState(loading = true)
        scope.launch {
            try {
                val items = searchProducts(query)
                _state.value = ProductUiState(products = items)
            } catch (t: Throwable) {
                _state.value = ProductUiState(error = t.message ?: "Unknown")
            }
        }
    }
}