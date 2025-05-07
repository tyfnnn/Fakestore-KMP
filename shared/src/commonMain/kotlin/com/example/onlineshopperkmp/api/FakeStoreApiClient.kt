package com.example.onlineshopperkmp.api

import com.example.onlineshopperkmp.models.Cart
import com.example.onlineshopperkmp.models.CartRequest
import com.example.onlineshopperkmp.models.LoginRequest
import com.example.onlineshopperkmp.models.LoginResponse
import com.example.onlineshopperkmp.models.Product
import com.example.onlineshopperkmp.models.ProductRequest
import com.example.onlineshopperkmp.models.User
import com.example.onlineshopperkmp.models.UserRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class FakeStoreApiClient(
    private val httpClient: HttpClient = createHttpClient()
) {
    companion object {
        private const val BASE_URL = "https://fakestoreapi.com"

        fun createHttpClient() = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                level = LogLevel.INFO
            }
        }
    }

    // Products
    suspend fun getAllProducts(): List<Product> =
        httpClient.get("$BASE_URL/products").body()

    suspend fun getProduct(id: Int): Product =
        httpClient.get("$BASE_URL/products/$id").body()

    suspend fun createProduct(product: ProductRequest): Product =
        httpClient.post("$BASE_URL/products") {
            contentType(ContentType.Application.Json)
            setBody(product)
        }.body()

    suspend fun updateProduct(id: Int, product: ProductRequest): Product =
        httpClient.put("$BASE_URL/products/$id") {
            contentType(ContentType.Application.Json)
            setBody(product)
        }.body()

    suspend fun deleteProduct(id: Int): Product =
        httpClient.delete("$BASE_URL/products/$id").body()

    // Categories
    suspend fun getAllCategories(): List<String> =
        httpClient.get("$BASE_URL/products/categories").body()

    suspend fun getProductsByCategory(category: String): List<Product> =
        httpClient.get("$BASE_URL/products/category/$category").body()

    // Cart
    suspend fun getAllCarts(): List<Cart> =
        httpClient.get("$BASE_URL/carts").body()

    suspend fun getCart(id: Int): Cart =
        httpClient.get("$BASE_URL/carts/$id").body()

    suspend fun getUserCarts(userId: Int): List<Cart> =
        httpClient.get("$BASE_URL/carts/user/$userId").body()

    suspend fun createCart(cart: CartRequest): Cart =
        httpClient.post("$BASE_URL/carts") {
            contentType(ContentType.Application.Json)
            setBody(cart)
        }.body()

    suspend fun updateCart(id: Int, cart: CartRequest): Cart =
        httpClient.put("$BASE_URL/carts/$id") {
            contentType(ContentType.Application.Json)
            setBody(cart)
        }.body()

    suspend fun deleteCart(id: Int): Cart =
        httpClient.delete("$BASE_URL/carts/$id").body()

    // User
    suspend fun getAllUsers(): List<User> =
        httpClient.get("$BASE_URL/users").body()

    suspend fun getUser(id: Int): User =
        httpClient.get("$BASE_URL/users/$id").body()

    suspend fun createUser(user: UserRequest): User =
        httpClient.post("$BASE_URL/users") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }.body()

    suspend fun updateUser(id: Int, user: UserRequest): User =
        httpClient.put("$BASE_URL/users/$id") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }.body()

    suspend fun deleteUser(id: Int): User =
        httpClient.delete("$BASE_URL/users/$id").body()

    // Login
    suspend fun login(username: String, password: String): LoginResponse =
        httpClient.post("$BASE_URL/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(username, password))
        }.body()
}