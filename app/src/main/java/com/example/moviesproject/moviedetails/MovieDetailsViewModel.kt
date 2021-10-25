package com.example.moviesproject.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.academy.fundamentals.homework.data.MovieRepository
import com.example.moviesproject.data.Movie
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _mutableMovieInfo = MutableLiveData<Movie>()

    val movieDetails : LiveData<Movie> = _mutableMovieInfo

    fun loadMovieDetail(id: Int){
        viewModelScope.launch {
            _mutableMovieInfo.postValue(repository.loadMovie(id))
        }

    }

}