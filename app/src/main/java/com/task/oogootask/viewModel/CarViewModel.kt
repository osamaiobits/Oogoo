package com.task.oogootask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.oogootask.repo.CarRepository
import com.task.oogootask.ui.data.Car
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(private val repository: CarRepository) : ViewModel() {

    val allCars: LiveData<List<Car>> = repository.allCars

    /*fun insertCar(car: Car) = viewModelScope.launch {
        repository.insertCar(car)
    }

    fun deleteAllCars() = viewModelScope.launch {
        repository.deleteAllCars()
    }*/

    fun insertSampleCars() = viewModelScope.launch {
        repository.insertSampleCars()
    }
}