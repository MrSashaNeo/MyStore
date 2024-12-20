package com.example.mystore.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.mystore.MainViewModel
import com.example.mystore.model.CartItem
import com.example.mystore.model.Product

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight


@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel = viewModel()) {
    val products by viewModel.products.collectAsState()
    val cart by viewModel.cart.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MyStore") },
                actions = {
                    IconButton(onClick = { navController.navigate("main") }) {  // Обновлено
                        Icon(Icons.Default.Search, contentDescription = "Поиск")
                    }
                    IconButton(onClick = { navController.navigate("cart") }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Корзина")
                    }
                    IconButton(onClick = { /* TODO: Перейти к личным сообщениям */ }) {
                        Icon(Icons.Default.Email, contentDescription = "Личные сообщения")
                    }
                    IconButton(onClick = { /* TODO: Перейти к личному кабинету */ }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Личный кабинет")
                    }
                }
            )
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(products) { product ->
                ProductCard(product, cart, navController, viewModel)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, cart: List<CartItem>, navController: NavHostController, viewModel: MainViewModel) {
    val isInCart = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { navController.navigate("product/${product.id}") }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(data = product.imageUrl),
                contentDescription = product.name,
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(product.name, fontWeight = FontWeight.Bold)
                Text("${product.price} ₽")
                Text("⭐ ${product.rating} (${product.reviewCount} отзывов)")
                IconButton(onClick = {
                    if (isInCart.value) {
                        viewModel.removeFromCart(product)
                    } else {
                        viewModel.addToCart(product)
                    }
                    isInCart.value = !isInCart.value
                }) {
                    Icon(
                        imageVector = if (isInCart.value) Icons.Default.Check else Icons.Default.AddShoppingCart,
                        contentDescription = if (isInCart.value) "Удалить из корзины" else "Добавить в корзину"
                    )
                }
            }
        }
    }
}
