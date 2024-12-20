package com.example.mystore.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mystore.MainViewModel
import com.example.mystore.screen.CartScreen
import com.example.mystore.screen.MainScreen
import com.example.mystore.screen.ProductDetailScreen

@Composable
fun AppNavigator(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController, viewModel)
        }
        composable("product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt()
            productId?.let { ProductDetailScreen(navController, it, viewModel) }
        }
        composable("cart") {
            CartScreen(navController, viewModel)
        }
    }
}
