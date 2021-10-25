package com.example.moviesproject.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.academy.fundamentals.homework.data.MovieRepository
import com.example.moviesproject.data.Movie
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository : MovieRepository
) :ViewModel() {

    private var _mutableMovieList = MutableLiveData<List<Movie>>()
    val liveDatamovieList : LiveData<List<Movie>> = _mutableMovieList

    fun loadMovieToLiveData() {
        viewModelScope.launch {
            _mutableMovieList.postValue(repository.loadMovies())
        }
    }
}