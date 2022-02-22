package com.example.moviesproject.presentation.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesproject.domain.use_cases.MoviesListUseCase
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val useCase: MoviesListUseCase
) : ViewModel() {

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _mutableListResult = MutableLiveData<MoviesListUseCase.Result>()
    val mutableListResult get() = _mutableListResult

    init {
        loadMovieToLiveData()
    }

    fun loadMovieToLiveData() {
        viewModelScope.launch {
            _isLoading.value = true
            when (useCase.getListResult()) {
                is MoviesListUseCase.Result.Success -> {
                    _mutableListResult.value =
                        MoviesListUseCase.Result.Success((useCase.getListResult() as MoviesListUseCase.Result.Success).movieList)
                }
                is MoviesListUseCase.Result.Error -> {
                    _mutableListResult.value =
                        MoviesListUseCase.Result.Error((useCase.getListResult() as MoviesListUseCase.Result.Error).error)
                }
            }
            _isLoading.value = false
        }
    }

    fun loadFreshMovieToLiveData() {
        viewModelScope.launch {
            _isLoading.value = true
            when (useCase.getFreshListResult()) {
                is MoviesListUseCase.Result.Success -> {
                    _mutableListResult.value =
                        MoviesListUseCase.Result.Success((useCase.getListResult() as MoviesListUseCase.Result.Success).movieList)
                }
                is MoviesListUseCase.Result.Error -> {
                    _mutableListResult.value =
                        MoviesListUseCase.Result.Error((useCase.getListResult() as MoviesListUseCase.Result.Error).error)
                }
            }
            _isLoading.value = false
        }
    }
}