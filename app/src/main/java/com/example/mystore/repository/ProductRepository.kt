package com.example.mystore.repository

import com.example.mystore.model.Product

object ProductRepository {
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Продукт 1", 1000.0, "https://example.com/product1.jpg", 4.5f, 10, "Описание продукта 1"),
            Product(2, "Продукт 2", 2000.0, "https://example.com/product2.jpg", 4.0f, 8, "Описание продукта 2")
            // Добавьте больше продуктов для тестирования
        )
    }
}
