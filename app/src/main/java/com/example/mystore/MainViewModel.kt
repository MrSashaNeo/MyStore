package com.example.mystore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.mystore.model.Product
import com.example.mystore.model.CartItem
import com.example.mystore.repository.ProductRepository

class MainViewModel : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _cart = MutableStateFlow<List<CartItem>>(emptyList())
    val cart: StateFlow<List<CartItem>> = _cart

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            delay(2000) // Задержка 2 секунды для имитации асинхронного вызова
            _products.value = ProductRepository.getProducts()
        }
    }

    fun addToCart(product: Product) {
        val updatedCart = _cart.value.toMutableList()
        updatedCart.add(CartItem(product))
        _cart.value = updatedCart
    }

    fun removeFromCart(product: Product) {
        val updatedCart = _cart.value.toMutableList()
        updatedCart.removeAll { it.product.id == product.id }
        _cart.value = updatedCart
    }

    fun getProductById(productId: Int): StateFlow<Product?> {
        return MutableStateFlow(_products.value.find { it.id == productId })
    }
}
