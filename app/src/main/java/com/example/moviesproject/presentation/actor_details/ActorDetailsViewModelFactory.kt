package com.example.moviesproject.presentation.actor_details

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesproject.App


class ActorDetailsViewModelFactory(
    private val appContext: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            ActorDetailsViewModel::class.java -> ActorDetailsViewModel(actorDetailsUseCase = (appContext as App).actorDetailsUseCase)
            else -> {
                throw IllegalStateException("Something wrong with ActorDetailsViewModel")
            }
        }
        return viewModel as T
    }
}