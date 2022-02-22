package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.repository.movie_list.MovieRepository
import retrofit2.HttpException
import java.io.IOException

class MoviesListUseCase(
    private val movieRepository: MovieRepository
) {
    sealed class Result {
        data class Success(val movieList: List<Movie>) : Result()
        data class Error(val error: String) : Result()
    }
    //TODO()
    suspend fun getListResult(): Result {
        return try {
            val list = movieRepository.loadMoviesList()
            Result.Success(list)
        } catch (e: HttpException) {
            Result.Error("Sorry we have server problems, try again later")
        } catch (e: IOException) {
            Result.Error("Couldn't reach the Server. Check your internet connection")
        }
    }

    suspend fun getFreshListResult(): Result {
        return try {
            val list = movieRepository.loadFreshMovieList()
            Result.Success(list)
        } catch (e: HttpException) {
            Result.Error("Sorry we have server problems, try again later")
        } catch (e: IOException) {
            Result.Error("Couldn't reach the Server. Check your internet connection")
        }
    }
}