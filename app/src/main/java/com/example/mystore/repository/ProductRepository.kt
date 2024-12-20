package com.example.mystore.repository

import com.example.mystore.R
import com.example.mystore.model.Product

object ProductRepository {
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Аниме фигурка", 1000.0, R.drawable.product1, 4.5f, 10, "Описание продукта 1"),
            Product(2, "PSP", 2000.0, R.drawable.product2, 4.0f, 8, "Описание продукта 2"),
            Product(3, "Ламборджини", 9999.0, R.drawable.product3, 3.5f, 10, "Описание продукта 3"),
            Product(4, "Шоколад Горький", 50.0, R.drawable.product4, 2.0f, 8, "Описание продукта 4"),
            Product(5, "Продукт 5", 1000.0, R.drawable.productx, 1.5f, 10, "Описание продукта 5"),
            Product(6, "Продукт 6", 2000.0, R.drawable.productx, 5.0f, 8, "Описание продукта 6"),
            Product(7, "Продукт 7", 3000.0, R.drawable.productx, 4.5f, 10, "Описание продукта 7"),
            Product(8, "Продукт 8", 4000.0, R.drawable.productx, 4.0f, 8, "Описание продукта 8"),
            // Добавьте больше продуктов для тестирования
        )
    }
}
