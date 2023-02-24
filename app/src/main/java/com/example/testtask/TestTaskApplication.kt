package com.example.testtask

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class TestTaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}