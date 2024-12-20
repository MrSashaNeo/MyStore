package com.example.mystore.repository

import com.example.mystore.R
import com.example.mystore.model.Product

object ProductRepository {
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Продукт 1", 1000.0, R.drawable.product1, 4.5f, 10, "Описание продукта 1"),
            Product(2, "Продукт 2", 2000.0, R.drawable.product2, 4.0f, 8, "Описание продукта 2"),
            Product(3, "Продукт 3", 1000.0, R.drawable.product3, 4.5f, 10, "Описание продукта 3"),
            Product(4, "Продукт 4", 2000.0, R.drawable.product4, 4.0f, 8, "Описание продукта 4"),
            // Добавьте больше продуктов для тестирования
        )
    }
}
