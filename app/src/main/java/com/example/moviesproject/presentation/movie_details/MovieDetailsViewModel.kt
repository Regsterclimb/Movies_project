package com.example.moviesproject.presentation.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.use_cases.MovieDetailsRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieDetailsRepository
) : ViewModel() {

    private val _mutableMovieInfo = MutableLiveData<MovieDetails>()

    val movieDetails : LiveData<MovieDetails> = _mutableMovieInfo

    fun loadMovieDetail(id: Int){
        viewModelScope.launch {
            _mutableMovieInfo.postValue(repository.loadMovie(id))
        }

    }

}