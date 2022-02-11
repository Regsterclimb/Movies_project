package com.example.moviesproject.data.repository

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.remote.NetworkModuleImpl
import com.example.moviesproject.data.repository.movie_details.MainMovieDetailsRepository
import com.example.moviesproject.data.repository.movie_details.ParseMovieDetails
import com.example.moviesproject.data.repository.movie_list.MainMoviesRepository
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.use_cases.MovieDetailsRepository


internal class MovieDetailsDataRepositoryImpl(
    private val context: Context,
    private val mainMoviesRep: MainMoviesRepository,
    private val parseMovieDetails: ParseMovieDetails,
    private val networkModule: NetworkModuleImpl,
    private val movieDetailsRep: MainMovieDetailsRepository
) : MovieDetailsRepository {

    init {
        Log.d("init", "MovieDetailsData")
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        return parseMovieDetails.parse(
            movieDetailsRep.getMovieDetailsApi(movieId, networkModule),
            mainMoviesRep.loadConfigurationFromApi(networkModule),
            movieDetailsRep.parseActorsData(
                movieId,
                mainMoviesRep.loadConfigurationFromApi(networkModule),
                networkModule
            )
        )
    }
}