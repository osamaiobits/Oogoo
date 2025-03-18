package com.task.oogootask.di

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.task.oogootask.utils.PreferenceManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mContext = applicationContext
        appContext = applicationContext
    }

    @SuppressLint("StaticFieldLeak")
    companion object{
        private lateinit var mContext: Context
        lateinit var mInstance: MyApplication
        lateinit var appContext: Context
    }

}