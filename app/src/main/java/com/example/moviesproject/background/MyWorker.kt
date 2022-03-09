package com.example.moviesproject.background

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.moviesproject.data.remote.network_module.NetworkModule
import com.example.moviesproject.data.storage.repository.movie_list.MainDataMoviesRepository
import com.example.moviesproject.data.storage.repository.movie_list.MovieDataRepositoryImpl
import com.example.moviesproject.data.storage.repository.movie_list.MoviesDataList
import com.example.moviesproject.data.storage.repository.movie_list.ParseMovieData

class MyWorker(
    context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val repository = MovieDataRepositoryImpl(
            applicationContext,
            MoviesDataList.Base(
                MainDataMoviesRepository.Base(
                    NetworkModule()
                ),
                ParseMovieData.Base()
            )
        )
        return try {
            repository.saveMoviesListDb(repository.getFreshMovieDataList())
            Log.d("MyWorker", "doing work")
            Result.success()
        } catch (exception: Exception) {
            Log.e("ERROR", "result failure at MyWorker")
            Result.failure()
        }
    }
}