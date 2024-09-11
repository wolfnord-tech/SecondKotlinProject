package ru.wolfnord.secondkotlinproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Вызов метода родительского класса
        enableEdgeToEdge() // Включаем отображение контента на границах экрана

        viewModel = ViewModelProvider(this)[MyViewModel::class.java] // Инициализируем ViewModel

        setContent {
            MainScreen(viewModel) // Устанавливаем основной экран приложения
        }
    }
}

// Главный экран приложения
@Composable
fun MainScreen(viewModel: MyViewModel) {
    val navController = rememberNavController() // Создаем NavController для навигации
    NavHost(navController, startDestination = "screen1") {
        composable("screen1") { Screen1(navController, viewModel) } // Первый экран
        composable("screen2") { Screen2(navController, viewModel) } // Второй экран
        composable("screen3") { Screen3(navController, viewModel) } // Третий экран
    }
}

// Экран 1: "Welcome Screen"
@Composable
fun Screen1(navController: NavController, viewModel: MyViewModel) {
    val count by viewModel.count.observeAsState() // Наблюдаем за счетом

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome to Screen 1", style = MaterialTheme.typography.titleLarge)
        Text(text = "Count: $count", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = { viewModel.increaseCount() }) {
            Text("Increase Count")
        }
        Button(onClick = { navController.navigate("screen2") }) {
            Text("Go to Screen 2")
        }
    }
}

// Экран 2: "Profile Screen"
@Composable
fun Screen2(navController: NavController, viewModel: MyViewModel) {
    val name by viewModel.name.observeAsState() // Наблюдаем за именем

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "This is Screen 2 - Your Profile", style = MaterialTheme.typography.titleLarge)
        TextField(
            value = name!!,
            onValueChange = { viewModel.setName(it) }, // Устанавливаем имя
            label = { Text("Enter your name") }
        )
        Text(text = "Hello, $name!", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = { navController.navigate("screen3") }) {
            Text("Go to Screen 3")
        }
    }
}

// Экран 3: "Settings Screen"
@Composable
fun Screen3(navController: NavController, viewModel: MyViewModel) {
    val isNotificationsEnabled by viewModel.state.observeAsState() // Наблюдаем за состоянием уведомлений

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Settings Screen - Adjust Preferences", style = MaterialTheme.typography.titleLarge)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Enable Notifications: ")
            Switch(
                checked = isNotificationsEnabled!!, // Текущее состояние переключателя
                onCheckedChange = { viewModel.changeState() } // Переключаем состояние
            )
        }
        Text(text = if (isNotificationsEnabled!!) "Notifications are enabled." else "Notifications are disabled.")
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Screen 2")
        }
    }
}
