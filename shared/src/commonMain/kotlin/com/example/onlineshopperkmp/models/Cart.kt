package com.example.onlineshopperkmp.models

import kotlinx.serialization.Serializable

@Serializable
data class Cart(
    val id: Int,
    val userId: Int,
    val date: String,
    val products: List<CartItem>
)

@Serializable
data class CartItem(
    val productId: Int,
    val quantity: Int
)

@Serializable
data class CartRequest(
    val userId: Int,
    val date: String,
    val products: List<CartItem>
)