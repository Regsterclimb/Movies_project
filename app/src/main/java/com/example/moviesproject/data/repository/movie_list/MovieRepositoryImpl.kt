package com.example.moviesproject.data.repository.movie_list

import com.example.moviesproject.data.di.MovieDataRepository
import com.example.moviesproject.data.model.MovieData
import com.example.moviesproject.domain.repository.movie_list.MovieRepository

class MovieRepositoryImpl(
    private val movieDataRepository: MovieDataRepository
) : MovieRepository {
    override suspend fun loadMoviesList(): List<MovieData> =
        movieDataRepository.getMoviesDataList()

    override suspend fun loadFreshMovieList(): List<MovieData> =
        movieDataRepository.getFreshMovieDataList()
}