package com.task.oogootask.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.oogootask.ui.data.Car
import com.task.oogootask.utils.Converters

@Database(entities = [Car::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao

    companion object {
        @Volatile
        private var INSTANCE: CarDatabase? = null

        fun getDatabase(context: Context): CarDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarDatabase::class.java,
                    "car_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}