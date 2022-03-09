package com.example.moviesproject.data.storage.repository.movie_details

import android.content.Context
import com.example.moviesproject.data.di.MovieDetailsDataRepository
import com.example.moviesproject.data.model.MovieDetailsData
import com.example.moviesproject.data.storage.extentions.toMovieDetailsData
import com.example.moviesproject.data.storage.extentions.toMovieDetailsEntity
import com.example.moviesproject.data.storage.movie_details.DetailsDataBase


class MovieDetailsDataRepositoryImpl(
    context: Context,
    private val moviesDetailsLoad: MoviesDetailsLoad
) : MovieDetailsDataRepository {

    private val detailsBase = DetailsDataBase.create(context).getMovieDetailsDao()

    override suspend fun loadMovieDetailsData(movieId: Int): MovieDetailsData =
        if ((detailsBase.getMovieDetails(movieId)) != null) {
            detailsBase.getMovieDetails(movieId)!!.toMovieDetailsData()
        } else {
            val details = loadFreshDetailsData(movieId)
            detailsBase.insertMovieDetails(details.toMovieDetailsEntity())
            details
        }

    override suspend fun loadFreshDetailsData(movieId: Int): MovieDetailsData {
        val details = moviesDetailsLoad.load(movieId)
        detailsBase.insertMovieDetails(details.toMovieDetailsEntity())
        return details
    }

}