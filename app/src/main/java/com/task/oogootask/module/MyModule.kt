package com.task.oogootask.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.task.oogootask.room.CarDao
import com.task.oogootask.room.CarDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import javax.net.ssl.*

@Module
@InstallIn(SingletonComponent::class)
class MyModule {

    private var context: Context? = null

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        context = application.applicationContext
        return context!!
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): CarDatabase {
        return Room.databaseBuilder(
            context,
            CarDatabase::class.java,
            "car_database"
        ).build()
    }

    @Provides
    fun provideCarDao(database: CarDatabase): CarDao {
        return database.carDao()
    }
}




