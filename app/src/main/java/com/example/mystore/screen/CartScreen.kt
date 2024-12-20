package com.example.mystore.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.mystore.MainViewModel
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CartScreen(navController: NavHostController, viewModel: MainViewModel = viewModel()) {
    val cart by viewModel.cart.collectAsState()
    var totalPrice by remember { mutableStateOf(0.0) }

    LaunchedEffect(cart) {
        totalPrice = cart.sumOf { it.product.price * it.quantity }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Корзина") },
                actions = {
                    IconButton(onClick = { navController.navigate("main") }) {
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
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(cart) { cartItem ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberImagePainter(data = cartItem.product.imageUrl),
                            contentDescription = cartItem.product.name,
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(cartItem.product.name, fontWeight = FontWeight.Bold)
                            Text("Цена: ${cartItem.product.price} ₽")
                            Text("Количество: ${cartItem.quantity}")
                        }
                        Spacer(modifier = Modifier.weight(1f))

                        // Добавление кнопок изменения количества и удаления
                        IconButton(onClick = { viewModel.updateQuantity(cartItem.product, cartItem.quantity + 1) }) {
                            Icon(Icons.Default.Add, contentDescription = "Увеличить количество")
                        }
                        IconButton(onClick = { viewModel.updateQuantity(cartItem.product, cartItem.quantity - 1) }) {
                            Icon(Icons.Default.Remove, contentDescription = "Уменьшить количество")
                        }
                        IconButton(onClick = { viewModel.removeFromCart(cartItem.product) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Удалить из корзины")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Общая стоимость: $totalPrice ₽", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { /* TODO: Реализация заказа товаров */ }) {
                    Text("Заказать товары")
                }
            }
        }
    }
}
