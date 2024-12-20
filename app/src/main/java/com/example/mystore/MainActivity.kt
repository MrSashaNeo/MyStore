package com.example.mystore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.mystore.screen.AppNavigator
import com.example.mystore.ui.theme.MyStoreTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStoreTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppContent(viewModel)
                }
            }
        }
    }
}

@Composable
fun AppContent(viewModel: MainViewModel) {
    val navController = rememberNavController()
    AppNavigator(navController, viewModel)
}
