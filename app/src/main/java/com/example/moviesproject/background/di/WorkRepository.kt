package com.example.moviesproject.background.di

import androidx.work.PeriodicWorkRequest

interface WorkRepository {
    fun saveListDataPeriodic() : PeriodicWorkRequest
}