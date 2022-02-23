package com.example.moviesproject.presentation.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase.DetailsResult
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase.DetailsResult.Error
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase.DetailsResult.Success
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val useCase: MovieDetailsUseCase
) : ViewModel() {

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _mutableDetailsResult = MutableLiveData<DetailsResult>()
    val mutableDetailsResult get() = _mutableDetailsResult

    fun loadMovieDetails(id: Int) = viewModelScope.launch {
        _isLoading.value = true
        when (val result = useCase.getDetailsResult(id)) {
            is Success -> _mutableDetailsResult.value =
                Success(result.movieDetails)
            is Error -> _mutableDetailsResult.value =
                Error(result.error)
        }
        _isLoading.value = false
    }

    fun loadFreshMovieToLiveData(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = useCase.getFreshDetailsResult(id)) {
                is Success -> _mutableDetailsResult.value =
                    Success(result.movieDetails)
                is Error -> _mutableDetailsResult.value =
                    Error(result.error)
            }
            _isLoading.value = false
        }
    }
}