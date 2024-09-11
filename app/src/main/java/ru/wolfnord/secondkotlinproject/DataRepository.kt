package ru.wolfnord.secondkotlinproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// Репозиторий, управляет состоянием данных
class DataRepository {
    private val _count = MutableLiveData(0) // MutableLiveData для счетчика, инициализируем со значением 0
    private val _name = MutableLiveData("") // MutableLiveData для имени, инициализируем со значением строковой пустоты
    private val _state = MutableLiveData(false) // MutableLiveData для переключателя, инициализируем со значением false

    // Возвращаем LiveData для счётчика
    fun getCount(): LiveData<Int> = _count

    // Возвращаем LiveData для имени
    fun getName(): LiveData<String> = _name

    // Возвращаем LiveData для переключателя
    fun getState(): LiveData<Boolean> = _state

    // Метод для увеличения счётчика
    fun increaseCount() {
        _count.value = (_count.value ?: 0) + 1 // Увеличиваем значение счетчика на 1
    }

    fun setName(name: String) {
        _name.value = name // Устанаваем новое значение имени
    }

    fun changeState() {
        _state.value = !_state.value!! // Меняем значение на противоположное
    }
}