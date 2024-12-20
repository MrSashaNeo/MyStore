package com.example.mystore.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.mystore.MainViewModel
import com.example.mystore.model.Product
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.IconButton
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ProductDetailScreen(navController: NavHostController, productId: Int, viewModel: MainViewModel = viewModel()) {
    val product: Product? by viewModel.getProductById(productId).collectAsState(initial = null)

    product?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Карточка товара") },
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
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                Image(
                    painter = rememberImagePainter(data = it.imageUrl),
                    contentDescription = it.name,
                    modifier = Modifier.size(400.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(it.name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Text("${it.price} ₽", fontSize = 20.sp)
                Text("⭐ ${it.rating} (${it.reviewCount} отзывов)")
                Text("Описание: ${it.description}")
                Spacer(modifier = Modifier.height(16.dp))

                // Добавляем Box для центрирования кнопки
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = { /* TODO: Реализация функционала заказа */ }) {
                        Text("Заказать товар")
                    }
                }
            }
        }
    }
}
