package com.example.moviesproject.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesproject.data.MovieDetailsRepository
import com.example.moviesproject.data.moviedata.MovieDetails
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieDetailsRepository,
) : ViewModel() {

    private val _mutableMovieInfo = MutableLiveData<MovieDetails>()

    val movieDetails : LiveData<MovieDetails> = _mutableMovieInfo

    fun loadMovieDetail(id: Int){
        viewModelScope.launch {
            _mutableMovieInfo.postValue(repository.loadMovie(id))
        }

    }

}