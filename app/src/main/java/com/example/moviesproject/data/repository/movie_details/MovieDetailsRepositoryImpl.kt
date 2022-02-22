package com.example.moviesproject.data.repository.movie_details

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.data_base.extentions.toMovieDetailsData
import com.example.moviesproject.data.data_base.movie_details.DetailsDataBase
import com.example.moviesproject.data.di.MovieDetailsDataRepository
import com.example.moviesproject.data.repository.movie_list.MainDataMoviesRepository
import com.example.moviesproject.domain.extentions.toMovieDetailsEntity


class MovieDetailsDataRepositoryImpl(
    context: Context,
    private val mainDataMoviesRep: MainDataMoviesRepository,
    private val parseMovieDetails: ParseMovieDetails,
    private val movieDetailsDataRep: MainMovieDetailsDataRepository
) : MovieDetailsDataRepository {
    private val detailsBase = DetailsDataBase.create(context).getMovieDetailsDao()

    init {
        Log.d("initStart", "MovieDetailsDataRepositoryImpl")
    }

    override suspend fun loadMovieDetailsData(movieId: Int): MovieDetailsData =
        if (detailsBase.getMovieDetails(movieId) != null) {
            detailsBase.getMovieDetails(movieId)!!.toMovieDetailsData()
        } else {
            val details = parseMovieDetails.parse(
                movieDetailsDataRep.getMovieDetailsApi(movieId),
                mainDataMoviesRep.loadConfigurationApi(),
                movieDetailsDataRep.parseActorsData(
                    movieId,
                    mainDataMoviesRep.loadConfigurationApi(),
                )
            )
            detailsBase.insertMovieDetails(details.toMovieDetailsEntity())
            details
        }
    protected fun finalize() {
        Log.d("initStart", "MovieDetailsDataRepositoryImpl finalize")
    }
}