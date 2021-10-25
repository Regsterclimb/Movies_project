package com.example.moviesproject.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.academy.fundamentals.homework.data.MovieRepository

@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory(
    val repository : MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            MovieListViewModel::class.java -> {
                MovieListViewModel(repository)
            }
            else -> throw IllegalStateException("somthing wrong modelClass")
        }
        return viewModel as T
    }
}

