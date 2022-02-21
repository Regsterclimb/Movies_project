package com.example.moviesproject.data.repository.movie_details

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.data_base.extentions.toMovieDetails
import com.example.moviesproject.data.data_base.movie_details.DetailsDataBase
import com.example.moviesproject.data.remote.NetworkModuleImpl
import com.example.moviesproject.data.repository.movie_list.MainMoviesRepository
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.model.toMovieEntity
import com.example.moviesproject.domain.use_cases.MovieDetailsRepository


class MovieDetailsDataRepositoryImpl(
    context: Context,
    private val mainMoviesRep: MainMoviesRepository,
    private val parseMovieDetails: ParseMovieDetails,
    private val networkModule: NetworkModuleImpl,
    private val movieDetailsRep: MainMovieDetailsRepository
) : MovieDetailsRepository {

    private val detailsBase = DetailsDataBase.create(context).getMovieDetailsDao()

    init {
        Log.d("init", "MovieDetailsData")
    }

    override suspend fun loadMovieDetails(movieId: Int): MovieDetails = // TODO()
        if (detailsBase.getMovieDetails(movieId) != null) {
            detailsBase.getMovieDetails(movieId)!!.toMovieDetails()
        } else {
            val details = parseMovieDetails.parse(
                movieDetailsRep.getMovieDetailsApi(movieId, networkModule),
                mainMoviesRep.loadConfigurationFromApi(networkModule),
                movieDetailsRep.parseActorsData(
                    movieId,
                    mainMoviesRep.loadConfigurationFromApi(networkModule),
                    networkModule
                )
            )
            detailsBase.insertMovieDetails(details.toMovieEntity())
            details
        }
}