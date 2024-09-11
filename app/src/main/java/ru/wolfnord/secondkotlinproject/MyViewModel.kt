package ru.wolfnord.secondkotlinproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

// ViewModel для управления логикой интерфейса
class MyViewModel: ViewModel() {
    private val _dataRepository = DataRepository() // Инициализируем репозиторий для управления данными

    // LiveData для хранения и наблюдения за состоянием счетчика
    val count: LiveData<Int> = _dataRepository.getCount()
    // LiveData для хранения и наблюдения за именем
    val name: LiveData<String> = _dataRepository.getName()
    // LiveData для хранения и наблюдения за состоянием
    val state: LiveData<Boolean> = _dataRepository.getState()


    // Метод для увеличения значения счетчика
    fun increaseCount() {
        _dataRepository.increaseCount()
    }

    // Метод для установки имени
    fun setName(name: String) {
        _dataRepository.setName(name)
    }

    // Метод для увеличения значения переключателя
    fun changeState() {
        _dataRepository.changeState()
    }
}
