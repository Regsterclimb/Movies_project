package com.example.moviesproject.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesproject.domain.use_cases.GetMovieRepository

@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory(
    private val repositoryGet: GetMovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            MovieListViewModel::class.java -> {
                MovieListViewModel(repositoryGet)
            }
            else -> throw IllegalStateException("som wrong modelClass")
        }
        return viewModel as T
    }
}

