package com.example.moviesproject.domain.use_cases.logic

import android.util.Log
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase.DetailsResult
import retrofit2.HttpException
import java.io.IOException

interface MovieDetailsLogic {

    suspend fun execute(function: suspend () -> MovieDetails): DetailsResult

    class Base : MovieDetailsLogic {

        override suspend fun execute(function: suspend () -> MovieDetails): DetailsResult =
            try {
                DetailsResult.Success(function.invoke())
            } catch (e: HttpException) {
                Log.e("SERVER", "SERVER FAIL")
                DetailsResult.Error("Sorry we have server problems, try again later")
            } catch (e: IOException) {
                Log.e("INTERNET", "INTERNET FAIL")
                DetailsResult.Error("Couldn't reach the Server. Check your internet connection")
            }
    }
}