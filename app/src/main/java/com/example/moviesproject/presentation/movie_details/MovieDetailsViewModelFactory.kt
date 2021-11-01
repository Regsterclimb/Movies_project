package com.example.moviesproject.presentation.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesproject.domain.use_cases.GetMovieDetailsRepository

class MovieDetailsViewModelFactory(
    val repositoryGet: GetMovieDetailsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass) {
            MovieDetailsViewModel::class.java -> MovieDetailsViewModel(repositoryGet)
            else -> throw IllegalStateException("Something wrong with MovieDetailsViewModel")
        }
        return viewModel as T
    }
}