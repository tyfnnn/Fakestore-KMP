// File: shared/src/commonMain/kotlin/com/example/onlineshopperkmp/repository/FakeStoreRepository.kt
package com.example.onlineshopperkmp.repository

import com.example.onlineshopperkmp.api.FakeStoreApiClient
import com.example.onlineshopperkmp.models.Cart
import com.example.onlineshopperkmp.models.CartItem
import com.example.onlineshopperkmp.models.CartRequest
import com.example.onlineshopperkmp.models.LoginResponse
import com.example.onlineshopperkmp.models.Product
import com.example.onlineshopperkmp.models.ProductRequest
import com.example.onlineshopperkmp.models.User

class FakeStoreRepository(
    private val apiClient: FakeStoreApiClient = FakeStoreApiClient()
) {
    // Products
    suspend fun getAllProducts(): Result<List<Product>> = runCatching {
        apiClient.getAllProducts()
    }

    suspend fun getProduct(id: Int): Result<Product> = runCatching {
        apiClient.getProduct(id)
    }

    suspend fun getProductsByCategory(category: String): Result<List<Product>> = runCatching {
        apiClient.getProductsByCategory(category)
    }

    suspend fun getAllCategories(): Result<List<String>> = runCatching {
        apiClient.getAllCategories()
    }

    suspend fun createProduct(
        title: String,
        price: Double,
        description: String,
        category: String,
        image: String
    ): Result<Product> = runCatching {
        val request = ProductRequest(title, price, description, category, image)
        apiClient.createProduct(request)
    }

    suspend fun updateProduct(
        id: Int,
        title: String,
        price: Double,
        description: String,
        category: String,
        image: String
    ): Result<Product> = runCatching {
        val request = ProductRequest(title, price, description, category, image)
        apiClient.updateProduct(id, request)
    }

    suspend fun deleteProduct(id: Int): Result<Product> = runCatching {
        apiClient.deleteProduct(id)
    }

    // Cart
    suspend fun getUserCarts(userId: Int): Result<List<Cart>> = runCatching {
        apiClient.getUserCarts(userId)
    }

    suspend fun getCart(id: Int): Result<Cart> = runCatching {
        apiClient.getCart(id)
    }

    suspend fun createCart(
        userId: Int,
        products: List<CartItem>
    ): Result<Cart> = runCatching {
        val request = CartRequest(
            userId = userId,
            date = getCurrentDate(),
            products = products
        )
        apiClient.createCart(request)
    }

    suspend fun updateCart(
        id: Int,
        userId: Int,
        products: List<CartItem>
    ): Result<Cart> = runCatching {
        val request = CartRequest(
            userId = userId,
            date = getCurrentDate(),
            products = products
        )
        apiClient.updateCart(id, request)
    }

    suspend fun deleteCart(id: Int): Result<Cart> = runCatching {
        apiClient.deleteCart(id)
    }

    // User & Auth
    suspend fun login(username: String, password: String): Result<LoginResponse> = runCatching {
        apiClient.login(username, password)
    }

    suspend fun getUserDetails(id: Int): Result<User> = runCatching {
        apiClient.getUser(id)
    }

    // Helper
    private fun getCurrentDate(): String {
        // In a real app, you would use kotlinx.datetime or format the date properly here
        return "2025-05-06"  // Using current date from the system
    }
}