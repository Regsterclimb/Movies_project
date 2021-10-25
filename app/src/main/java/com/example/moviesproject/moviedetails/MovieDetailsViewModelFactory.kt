package com.example.moviesproject.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.academy.fundamentals.homework.data.MovieRepository

class MovieDetailsViewModelFactory(
    val repository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass) {
            MovieDetailsViewModel::class.java -> MovieDetailsViewModel(repository)
            else -> throw IllegalStateException("Something wrong with MovieDetailsViewModel")
        }
        return viewModel as T
    }
}