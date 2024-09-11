package ru.wolfnord.secondkotlinproject

import android.os.Bundle // Импортируем класс для работы с жизненным циклом Activity
import androidx.activity.ComponentActivity // Импортируем класс для создания компонента Activity
import androidx.activity.compose.setContent // Импортируем метод для установки содержимого Activity с помощью Compose
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.* // Импортируем контейнеры для компоновки. Включает в себя Column, Row и т.д.
import androidx.compose.material3.* // Импортируем Material Design 3 компоненты, такие как Text и Button
import androidx.compose.runtime.Composable // Импортируем аннотацию @Composable для функции, представляющей экран
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController // Импортируем класс для управления навигацией
import androidx.navigation.compose.* // Импортируем навигационные функции Compose
import androidx.compose.ui.Alignment // Импортируем класс для выравнивания элементов
import androidx.compose.ui.Modifier // Импортируем класс для модификации компоновки

class MainActivity : ComponentActivity() {
    // Метод, вызываемый при создании Активити
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Вызов родительского метода для корректной работы
        enableEdgeToEdge()
        setContent { // Устанавливаем содержание Activity
            MainScreen() // Вызываем функцию основного экрана приложения
        }
    }
}

// Главный экран приложения
@Composable
fun MainScreen() {
    // Создаем экземпляр NavController для управления навигацией между экранами
    val navController = rememberNavController()
    // Настраиваем NavHost для управления навигацией
    NavHost(navController, startDestination = "screen1") { // Navigation, если много экранов
        // Определяем первый экран
        composable("screen1") { Screen1(navController) }
        // Определяем второй экран
        composable("screen2") { Screen2(navController) }
        // Определяем третий экран
        composable("screen3") { Screen3(navController) }
    }
}

// Экран 1: "Welcome Screen"
@Composable
fun Screen1(navController: NavController) {
    // Состояние для хранение текущего значения счетчика
    var count by remember { mutableIntStateOf(0) } // Приравнивать к равно = больше функций

    // Используем Column для вертикальной компоновки элементов
    Column(
        modifier = Modifier.fillMaxSize(), // Занимаем всю доступную площадь = wrapContent (Удобно при работе с текстом)
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем элементы по горизонтали
        verticalArrangement = Arrangement.Center // Центрируем элементы по вертикали
    ) {
        // Текст на экране
        Text(text = "Welcome to Screen 1", style = MaterialTheme.typography.titleLarge)
        // Текущий счетчик отображается как текст
        Text(text = "Count: $count", style = MaterialTheme.typography.bodyLarge)
        // Кнопка для увеличения счетчика
        Button(onClick = { count++ }) {
            Text("Increase Count") // Текст на кнопке
        }

        // Кнопка, переходящая на экран 2
        Button(onClick = { navController.navigate("screen2") }) {
            Text("Go to Screen 2") // Текст на кнопке
        }
    }
}

// Экран 2: "Profile Screen"
@Composable
fun Screen2(navController: NavController) {
    var name by remember { mutableStateOf("") }
    // Используем Colomn для вертикальной компоновки элементов
    Column(
        modifier = Modifier.fillMaxSize(), // Занимаем всю доступную площадь
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем элементы по горизонтали
        verticalArrangement = Arrangement.Center // Центрируем элементы по вертикали
    ) {
        // Текст на экране
        Text(text = "This is Screen 2 - Your Profile", style = MaterialTheme.typography.titleLarge)

        // Поле для ввода имени
        TextField(
            value = name, // Текущее значение поля
            onValueChange = { name = it }, // Обновление имени при вводе
            label = { Text("Enter your name") } // Метка для текстового поля
        )

        // Приветствие с введенным именем
        Text(text = "Hello, $name!", style = MaterialTheme.typography.bodyLarge)

        // Кнопка, переходящая на экран 3
        Button(onClick = { navController.navigate("screen3") }) {
            Text("Go to Screen 3") // Текст на кнопке
        }
    }
}

// Экран 3: "Settings Screen"
@Composable
fun Screen3(navController: NavController) {
    var isNotificationsEnabled by remember { mutableStateOf(false) }

    // Используем Colomn для вертикальной компоновки элементов
    Column(
        modifier = Modifier.fillMaxSize(), // Занимает всю доступную площадь
        horizontalAlignment = Alignment.CenterHorizontally, // Центрируем элементы по горизонтали
        verticalArrangement = Arrangement.Center // Центрируем элементы по вертикали
    ) {
        // Текст на экране
        Text(text = "Settings Screen - Adjust Preferences", style = MaterialTheme.typography.titleLarge)
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Текст рядом с переключателем
            Text("Enable Notifications: ")
            // Переключатель для управления уведомлениями
            Switch(
                checked = isNotificationsEnabled, // Текущее состояние переключателя
                onCheckedChange = { isNotificationsEnabled = it } // Обновления состояния уведомлений
            )
        }
        // Текст, отображающий текущее состояние уведомлений
        Text(text = if (isNotificationsEnabled) "Notification are enabled." else "Notifications are disabled.")
        // Кнопка, переходящая на экран 1
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Screen 2") // Текст на кнопке
        }
    }
}