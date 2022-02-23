package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.use_cases.MoviesListUseCase.ListResult
import retrofit2.HttpException
import java.io.IOException

interface MovieListCatch {

    suspend fun execute(list: List<Movie>): ListResult

    class Base : MovieListCatch {
        override suspend fun execute(list: List<Movie>): ListResult =
            try {
                ListResult.Success(list)
            } catch (e: HttpException) {
                ListResult.Error("Sorry we have server problems, try again later")
            } catch (e: IOException) {
                ListResult.Error("Couldn't reach the Server. Check your internet connection")
            }
    }
}