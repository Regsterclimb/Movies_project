package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.extentions.toMovie
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.repository.movie_list.MovieRepository
import com.example.moviesproject.domain.use_cases.logic.MovieListLogic

class MoviesListUseCase(
    private val movieRepository: MovieRepository,
    private val movieListLogic: MovieListLogic
) {
    sealed class ListResult {
        class Success(val movieList: List<Movie>) : ListResult()
        class Error(val error: String) : ListResult()
    }

    suspend fun getListResult(): ListResult = movieListLogic.execute {
        movieRepository.loadMoviesList().map { movieData ->
            movieData.toMovie()
        }
    }

    suspend fun getFreshListResult(): ListResult = movieListLogic.execute {
        movieRepository.loadFreshMovieList().map { movieData ->
            movieData.toMovie()
        }
    }
}