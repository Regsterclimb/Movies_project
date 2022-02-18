package com.example.moviesproject.presentation.movie_details

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesproject.data.remote.NetworkModuleImpl
import com.example.moviesproject.data.repository.MovieDetailsDataRepositoryImpl
import com.example.moviesproject.data.repository.movie_details.MainMovieDetailsRepository
import com.example.moviesproject.data.repository.movie_details.ParseMovieDetails
import com.example.moviesproject.data.repository.movie_list.MainMoviesRepository

class MovieDetailsViewModelFactory(
    private val applicationContext: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            MovieDetailsViewModel::class.java -> MovieDetailsViewModel(
                repository = MovieDetailsDataRepositoryImpl(
                    applicationContext,
                    MainMoviesRepository.Base(),
                    ParseMovieDetails.Base(),
                    NetworkModuleImpl(),
                    MainMovieDetailsRepository.Base(
                        MainMoviesRepository.Base()
                    )
                )
            )
            else -> throw IllegalStateException("Something wrong with MovieDetailsViewModel")
        }
        return viewModel as T
    }
}