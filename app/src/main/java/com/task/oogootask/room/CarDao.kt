package com.task.oogootask.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.task.oogootask.ui.data.Car

@Dao
interface CarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Query("SELECT * FROM cars ORDER BY postDate DESC")
    fun getAllCars(): LiveData<List<Car>>

    @Query("DELETE FROM cars")
    suspend fun deleteAllCars()
}