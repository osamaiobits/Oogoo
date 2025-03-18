package com.task.oogootask.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val carName: String,
    val carDetails: String,
    val carModel: String,
    val carPrice: Double,
    val carKm: Int,
    val carBrand: String,
    val status: String,
    val postDate: Long,
    val listOfImages: List<CarImages>
)