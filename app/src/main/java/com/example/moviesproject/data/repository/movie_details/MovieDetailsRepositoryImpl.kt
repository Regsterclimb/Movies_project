package com.example.moviesproject.data.repository.movie_details

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.data_base.extentions.toMovieDetailsData
import com.example.moviesproject.data.data_base.movie_details.DetailsDataBase
import com.example.moviesproject.data.repository.movie_list.MainMoviesRepository
import com.example.moviesproject.domain.extentions.toMovieDetailsEntity
import com.example.moviesproject.domain.use_cases.MovieDetailsRepository


class MovieDetailsDataRepositoryImpl(
    context: Context,
    private val mainMoviesRep: MainMoviesRepository,
    private val parseMovieDetails: ParseMovieDetails,
    private val movieDetailsRep: MainMovieDetailsRepository
) : MovieDetailsRepository {
    private val detailsBase = DetailsDataBase.create(context).getMovieDetailsDao()

    init {
        Log.d("init", "MovieDetailsData")
    }

    override suspend fun loadMovieDetails(movieId: Int): MovieDetailsData = //
        if (detailsBase.getMovieDetails(movieId) != null) {
            detailsBase.getMovieDetails(movieId)!!.toMovieDetailsData()
        } else {
            val details = parseMovieDetails.parse(
                movieDetailsRep.getMovieDetailsApi(movieId),
                mainMoviesRep.loadConfigurationApi(),
                movieDetailsRep.parseActorsData(
                    movieId,
                    mainMoviesRep.loadConfigurationApi(),
                )
            )
            detailsBase.insertMovieDetails(details.toMovieDetailsEntity())
            details
        }
}