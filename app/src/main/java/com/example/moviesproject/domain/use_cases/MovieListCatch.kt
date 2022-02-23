package com.example.moviesproject.domain.use_cases

import android.util.Log
import com.example.moviesproject.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

interface MovieListCatch {

    suspend fun execute(function: suspend () -> List<Movie>): MoviesListUseCase.ListResult

    class Base : MovieListCatch {
        override suspend fun execute(function: suspend () -> List<Movie>): MoviesListUseCase.ListResult =
            try {
                val list = function.invoke()
                MoviesListUseCase.ListResult.Success(list)
            } catch (e: HttpException) {
                Log.e("SERVER", "SERVER FAIL")
                MoviesListUseCase.ListResult.Error("Sorry we have server problems, try again later")
            } catch (e: IOException) {
                Log.e("INTERNET", "INTERNET FAIL")
                MoviesListUseCase.ListResult.Error("Couldn't reach the Server. Check your internet connection")
            }
    }
}