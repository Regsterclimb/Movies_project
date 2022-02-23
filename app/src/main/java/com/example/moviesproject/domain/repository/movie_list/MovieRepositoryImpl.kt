package com.example.moviesproject.domain.repository.movie_list

import com.example.moviesproject.data.di.MovieDataRepository
import com.example.moviesproject.domain.extentions.toMovie
import com.example.moviesproject.domain.model.Movie

class MovieRepositoryImpl(
    private val movieDataRepository: MovieDataRepository
) : MovieRepository {
    override suspend fun loadMoviesList(): List<Movie> =
        movieDataRepository.getMoviesDataList().map { movieData ->
            movieData.toMovie()
        }

    override suspend fun loadFreshMovieList(): List<Movie> =
        movieDataRepository.getRefreshedList().map { movieData ->
            movieData.toMovie()
        }
}