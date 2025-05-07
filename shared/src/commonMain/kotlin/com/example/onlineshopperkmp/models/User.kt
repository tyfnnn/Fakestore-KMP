package com.example.onlineshopperkmp.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val name: Name,
    val address: Address,
    val phone: String
)

@Serializable
data class Name(
    val firstname: String,
    val lastname: String
)

@Serializable
data class Address(
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String,
    val geolocation: Geolocation
)

@Serializable
data class Geolocation(
    val lat: String,
    val long: String
)

@Serializable
data class UserRequest(
    val email: String,
    val username: String,
    val password: String,
    val name: Name,
    val address: Address,
    val phone: String
)
