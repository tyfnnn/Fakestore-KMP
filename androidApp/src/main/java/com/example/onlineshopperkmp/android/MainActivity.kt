package com.example.onlineshopperkmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.onlineshopperkmp.Greeting
import com.example.onlineshopperkmp.viewmodels.ProductsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ProductsViewModel()

        setContent {
            OnlineShopperTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OnlineShopperApp(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun OnlineShopperApp(viewModel: ProductsViewModel) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Products) }

    when (val screen = currentScreen) {
        is Screen.Products -> {
            ProductsScreen(
                viewModel = viewModel,
                onProductClick = { productId ->
                    // Navigate to details screen
                    currentScreen = Screen.ProductDetails
                }
            )
        }
        is Screen.ProductDetails -> {
            ProductDetailsScreen(
                viewModel = viewModel,
                onBackPressed = {
                    // Navigate back to products screen
                    currentScreen = Screen.Products
                }
            )
        }
    }
}

// Simple navigation model
sealed class Screen {
    object Products : Screen()
    object ProductDetails : Screen()
}

// Theme definition would typically go in a separate file
@Composable
fun OnlineShopperTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}