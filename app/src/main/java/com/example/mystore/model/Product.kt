package com.example.mystore.model

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val rating: Float,
    val reviewCount: Int,
    val description: String
)
