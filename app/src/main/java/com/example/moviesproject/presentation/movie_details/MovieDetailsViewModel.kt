package com.example.moviesproject.presentation.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesproject.data.repository.movie_details.MovieDetailsDataRepositoryImpl
import com.example.moviesproject.domain.model.MovieDetails
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieDetailsDataRepositoryImpl
) : ViewModel() {

    private val _mutableMovieInfo = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _mutableMovieInfo

    fun loadMovieDetail(id: Int) {
        viewModelScope.launch {
            _mutableMovieInfo.postValue(repository.loadMovieDetails(id))
        }
    }
}