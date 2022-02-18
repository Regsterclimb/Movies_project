package com.example.moviesproject.presentation.movie_list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesproject.data.remote.NetworkModuleImpl
import com.example.moviesproject.data.repository.movie_list.MainMoviesRepository
import com.example.moviesproject.data.repository.movie_list.MovieRepositoryImpl
import com.example.moviesproject.data.repository.movie_list.ParseMovie

class MovieListViewModelFactory(
    private val appContext: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            MovieListViewModel::class.java -> {
                MovieListViewModel(
                    repository = MovieRepositoryImpl(
                        appContext,
                        ParseMovie.Base(),
                        MainMoviesRepository.Base(),
                        NetworkModuleImpl()
                    )
                )
            }
            else -> throw IllegalStateException("som wrong modelClass")
        }
        return viewModel as T
    }
}

