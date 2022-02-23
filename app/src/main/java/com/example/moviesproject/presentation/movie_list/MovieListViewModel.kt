package com.example.moviesproject.presentation.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesproject.domain.use_cases.MoviesListUseCase
import com.example.moviesproject.domain.use_cases.MoviesListUseCase.ListResult
import com.example.moviesproject.domain.use_cases.MoviesListUseCase.ListResult.Error
import com.example.moviesproject.domain.use_cases.MoviesListUseCase.ListResult.Success
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val useCase: MoviesListUseCase
) : ViewModel() {

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _mutableListResult = MutableLiveData<ListResult>()
    val mutableListResult get() = _mutableListResult

    init {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = useCase.getListResult()) {
                is Success -> _mutableListResult.value = Success(result.movieList)
                is Error -> _mutableListResult.value = Error(result.error)
            }
            _isLoading.value = false
        }
    }

    fun loadFreshMovieToLiveData() {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = useCase.getFreshListResult()) {
                is Success -> _mutableListResult.value = Success(result.movieList)
                is Error -> _mutableListResult.value = Error(result.error)
            }
            _isLoading.value = false
        }
    }
}