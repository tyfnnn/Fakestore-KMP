package com.example.onlineshopperkmp.viewmodels

import com.example.onlineshopperkmp.models.Product
import com.example.onlineshopperkmp.repository.FakeStoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val repository: FakeStoreRepository = FakeStoreRepository(),
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState: StateFlow<ProductsUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
        loadCategories()
    }

    fun loadProducts() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        coroutineScope.launch {
            repository.getAllProducts()
                .onSuccess { products ->
                    _uiState.update {
                        it.copy(
                            products = products,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            error = error.message,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun loadCategories() {
        coroutineScope.launch {
            repository.getAllCategories()
                .onSuccess { categories ->
                    _uiState.update { it.copy(categories = categories) }
                }
                .onFailure { /* Handle error if needed */ }
        }
    }

    fun loadProductsByCategory(category: String) {
        _uiState.update { it.copy(isLoading = true, error = null) }

        coroutineScope.launch {
            repository.getProductsByCategory(category)
                .onSuccess { products ->
                    _uiState.update {
                        it.copy(
                            products = products,
                            isLoading = false,
                            selectedCategory = category
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            error = error.message,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun getProductDetails(productId: Int) {
        _uiState.update { it.copy(isLoadingDetails = true, detailsError = null) }

        coroutineScope.launch {
            repository.getProduct(productId)
                .onSuccess { product ->
                    _uiState.update {
                        it.copy(
                            selectedProduct = product,
                            isLoadingDetails = false
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            detailsError = error.message,
                            isLoadingDetails = false
                        )
                    }
                }
        }
    }
}

data class ProductsUiState(
    val isLoading: Boolean = false,
    val isLoadingDetails: Boolean = false,
    val products: List<Product> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String? = null,
    val selectedProduct: Product? = null,
    val error: String? = null,
    val detailsError: String? = null
)

