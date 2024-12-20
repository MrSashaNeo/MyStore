package com.example.mystore.model

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: Int,  // Изменено на Int для ресурса
    val rating: Float,
    val reviewCount: Int,
    val description: String
)
