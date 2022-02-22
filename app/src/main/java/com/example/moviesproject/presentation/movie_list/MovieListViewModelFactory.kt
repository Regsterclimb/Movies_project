package com.example.moviesproject.presentation.movie_list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesproject.App

class MovieListViewModelFactory(
    private val appContext: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            MovieListViewModel::class.java -> {
                MovieListViewModel(useCase = (appContext as App).movieDataUseCase)
            }
            else -> throw IllegalStateException("som wrong modelClass")
        }
        return viewModel as T
    }
}

