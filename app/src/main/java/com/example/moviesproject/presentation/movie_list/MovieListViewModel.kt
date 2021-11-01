package com.example.moviesproject.presentation.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.use_cases.GetMovieRepository
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repositoryGet : GetMovieRepository
) :ViewModel() {

    private var _mutableMovieList = MutableLiveData<List<Movie>>()
    val liveDatamovieList : LiveData<List<Movie>> = _mutableMovieList

    fun loadMovieToLiveData() {
        viewModelScope.launch {
            _mutableMovieList.postValue(repositoryGet.loadMovies())
        }
    }
}