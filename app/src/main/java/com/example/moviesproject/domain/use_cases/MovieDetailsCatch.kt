package com.example.moviesproject.domain.use_cases

import android.util.Log
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase.DetailsResult
import retrofit2.HttpException
import java.io.IOException

interface MovieDetailsCatch {

    suspend fun execute(function: suspend () -> MovieDetails): DetailsResult

    class Base : MovieDetailsCatch {
        override suspend fun execute(function: suspend () -> MovieDetails): DetailsResult =
            try {
                val details = function.invoke()
                DetailsResult.Success(details)
            } catch (e: HttpException) {
                Log.e("SERVER", "SERVER FAIL")
                DetailsResult.Error("Sorry we have server problems, try again later")
            } catch (e: IOException) {
                Log.e("INTERNET", "INTERNET FAIL")
                DetailsResult.Error("Couldn't reach the Server. Check your internet connection")
            }
    }
}