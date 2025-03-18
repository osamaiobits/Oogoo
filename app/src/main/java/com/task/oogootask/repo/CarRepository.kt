package com.task.oogootask.repo

import androidx.lifecycle.LiveData
import com.task.oogootask.room.CarDao
import com.task.oogootask.ui.data.Car
import com.task.oogootask.ui.data.CarImages
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class CarRepository @Inject constructor(private val carDao: CarDao) {

    val allCars: LiveData<List<Car>> = carDao.getAllCars()

    suspend fun insertCar(car: Car) {
        carDao.insertCar(car)
    }

    suspend fun deleteAllCars() {
        carDao.deleteAllCars()
    }

    // Function to insert sample data
    suspend fun insertSampleCars() {
        withContext(Dispatchers.IO) {
            val sampleCars = listOf(
                Car(
                    carName = "Tesla Model S",
                    carDetails = "Electric, Autopilot, Long Range",
                    carModel = "2023",
                    carPrice = 79999.99,
                    carKm = 5000,
                    carBrand = "Tesla",
                    status = "Available",
                    postDate = System.currentTimeMillis(),
                    listOfImages = listOf(
                        CarImages("img_car1_1"),
                        CarImages("img_car1_2"),
                        CarImages("img_car1_3"),
                        CarImages("img_car1_4")
                    )
                ),
                Car(
                    carName = "BMW M4",
                    carDetails = "Sporty, Twin-Turbo, 503 HP",
                    carModel = "2022",
                    carPrice = 71999.99,
                    carKm = 10000,
                    carBrand = "BMW",
                    status = "Sold",
                    postDate = System.currentTimeMillis(),
                    listOfImages = listOf(
                        CarImages("img_car2_1"),
                        CarImages("img_car2_2")
                    )
                ),
                Car(
                    carName = "Audi R8",
                    carDetails = "V10 Engine, Supercar Performance",
                    carModel = "2021",
                    carPrice = 142000.00,
                    carKm = 3000,
                    carBrand = "Audi",
                    status = "Available",
                    postDate = System.currentTimeMillis(),
                    listOfImages = listOf(
                        CarImages("img_car3_1"),
                        CarImages("img_car3_2"),
                        CarImages("img_car3_3"),
                        CarImages("img_car3_4")
                    )
                ),
                Car(
                    carName = "Mercedes AMG GT",
                    carDetails = "Handcrafted V8, 630 HP, Luxury & Performance",
                    carModel = "2023",
                    carPrice = 118000.00,
                    carKm = 7000,
                    carBrand = "Mercedes",
                    status = "Available",
                    postDate = System.currentTimeMillis(),
                    listOfImages = listOf(
                        CarImages("img_car4_1"),
                        CarImages("img_car4_2"),
                        CarImages("img_car4_3")
                    )
                ),
                Car(
                    carName = "Porsche 911 Turbo S",
                    carDetails = "640 HP, AWD, Timeless Icon",
                    carModel = "2022",
                    carPrice = 203500.00,
                    carKm = 5000,
                    carBrand = "Porsche",
                    status = "Sold",
                    postDate = System.currentTimeMillis(),
                    listOfImages = listOf(
                        CarImages("img_car5_1"),
                        CarImages("img_car5_2"),
                        CarImages("img_car5_3"),
                        CarImages("img_car5_4")
                    )
                ),
                Car(
                    carName = "Ford Mustang Shelby GT500",
                    carDetails = "Supercharged V8, Muscle Car Beast",
                    carModel = "2023",
                    carPrice = 95000.00,
                    carKm = 12000,
                    carBrand = "Ford",
                    status = "Available",
                    postDate = System.currentTimeMillis(),
                    listOfImages = listOf(
                        CarImages("img_car6_1"),
                        CarImages("img_car6_2")
                    )
                ),
                Car(
                    carName = "Chevrolet Corvette C8",
                    carDetails = "Mid-Engine, Supercar Performance",
                    carModel = "2021",
                    carPrice = 69000.00,
                    carKm = 8000,
                    carBrand = "Chevrolet",
                    status = "Available",
                    postDate = System.currentTimeMillis(),
                    listOfImages = listOf(
                        CarImages("img_car7_1"),
                        CarImages("img_car7_2"),
                        CarImages("img_car7_3")
                    )
                ),
                Car(
                    carName = "Lamborghini Huracan EVO",
                    carDetails = "V10, Aerodynamic Design, AWD",
                    carModel = "2022",
                    carPrice = 245000.00,
                    carKm = 3500,
                    carBrand = "Lamborghini",
                    status = "Sold",
                    postDate = System.currentTimeMillis(),
                    listOfImages = listOf(
                        CarImages("img_car8_1"),
                        CarImages("img_car8_2"),
                        CarImages("img_car8_3")
                    )
                ),
                Car(
                    carName = "Ferrari F8 Tributo",
                    carDetails = "710 HP, Twin-Turbo V8, Exotic Beauty",
                    carModel = "2023",
                    carPrice = 280000.00,
                    carKm = 2500,
                    carBrand = "Ferrari",
                    status = "Available",
                    postDate = System.currentTimeMillis(),
                    listOfImages = listOf(
                        CarImages("img_car9_1"),
                        CarImages("img_car9_2"),
                        CarImages("img_car9_3"),
                        CarImages("img_car9_4")
                    )
                ),
                Car(
                    carName = "McLaren 720S",
                    carDetails = "Twin-Turbo V8, Ultra-Lightweight, 710 HP",
                    carModel = "2021",
                    carPrice = 299000.00,
                    carKm = 4000,
                    carBrand = "McLaren",
                    status = "Available",
                    postDate = System.currentTimeMillis(),
                    listOfImages = listOf(
                        CarImages("img_car10_1"),
                        CarImages("img_car10_2"),
                        CarImages("img_car10_3"),
                        CarImages("img_car10_4")
                    )
                )
            )

            sampleCars.forEach { carDao.insertCar(it) }
        }
    }

}