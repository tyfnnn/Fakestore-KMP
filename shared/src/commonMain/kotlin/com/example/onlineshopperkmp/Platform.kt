package com.example.onlineshopperkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform