package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase.DetailsResult
import retrofit2.HttpException
import java.io.IOException

interface MovieDetailsCatch {

    suspend fun execute(movieDetails: MovieDetails): DetailsResult

    class Base : MovieDetailsCatch {
        override suspend fun execute(movieDetails: MovieDetails): DetailsResult =
            try {
                DetailsResult.Success(movieDetails)
            } catch (e: HttpException) {
                DetailsResult.Error("Sorry we have server problems, try again later")
            } catch (e: IOException) {
                DetailsResult.Error("Couldn't reach the Server. Check your internet connection")
            }
    }
}