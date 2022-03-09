package com.example.moviesproject.background

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import com.example.moviesproject.background.di.WorkRepository
import java.util.concurrent.TimeUnit

class WorkRepositoryImpl : WorkRepository {

    private val constraint = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .build()

    override fun saveListDataPeriodic(): PeriodicWorkRequest =
        PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraint).build()
}