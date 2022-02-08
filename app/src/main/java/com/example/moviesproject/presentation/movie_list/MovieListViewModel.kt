package com.example.moviesproject.presentation.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.use_cases.MovieRepository
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository : MovieRepository
) :ViewModel() {

    private var _mutableMovieList = MutableLiveData<List<Movie>>()
    val liveDataMovieList : LiveData<List<Movie>> = _mutableMovieList

    fun loadMovieToLiveData() {
        viewModelScope.launch {
            _mutableMovieList.postValue(repository.loadMovies())
        }
    }
}