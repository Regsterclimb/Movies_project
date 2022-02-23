package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.repository.movie_list.MovieRepository

class MoviesListUseCase(
    private val movieRepository: MovieRepository,
    private val movieListCatch: MovieListCatch
) {
    sealed class ListResult {
        data class Success(val movieList: List<Movie>) : ListResult()
        data class Error(val error: String) : ListResult()
    }

    suspend fun getListResult(): ListResult = movieListCatch.execute(movieRepository.loadMoviesList())

    suspend fun getFreshListResult(): ListResult =
        movieListCatch.execute(movieRepository.loadFreshMovieList())

}