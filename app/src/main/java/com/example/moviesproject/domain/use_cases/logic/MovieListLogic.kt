package com.example.moviesproject.domain.use_cases.logic

import android.util.Log
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.use_cases.MoviesListUseCase
import retrofit2.HttpException
import java.io.IOException

interface MovieListLogic {

    suspend fun execute(loadList: suspend () -> List<Movie>): MoviesListUseCase.ListResult

    class Base : MovieListLogic {

        override suspend fun execute(loadList: suspend () -> List<Movie>): MoviesListUseCase.ListResult =
            try {
                MoviesListUseCase.ListResult.Success(loadList.invoke())
            } catch (e: HttpException) {
                Log.e("SERVER", "SERVER FAIL")
                MoviesListUseCase.ListResult.Error("Sorry we have server problems, try again later")
            } catch (e: IOException) {
                Log.e("INTERNET", "INTERNET FAIL")
                MoviesListUseCase.ListResult.Error("Couldn't reach the Server. Check your internet connection")
            }
    }
}