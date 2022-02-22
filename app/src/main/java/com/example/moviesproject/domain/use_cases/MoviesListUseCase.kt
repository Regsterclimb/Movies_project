package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.repository.movie_list.MovieRepository

class MoviesListUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun loadList(): List<Movie> = movieRepository.loadMoviesList()
}