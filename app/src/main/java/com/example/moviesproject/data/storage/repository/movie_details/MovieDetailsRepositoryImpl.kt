package com.example.moviesproject.data.repository.movie_details

import android.content.Context
import com.example.moviesproject.data.di.MovieDetailsDataRepository
import com.example.moviesproject.data.model.MovieDetailsData
import com.example.moviesproject.data.storage.extentions.toMovieDetailsData
import com.example.moviesproject.data.storage.movie_details.DetailsDataBase
import com.example.moviesproject.data.storage.repository.movie_details.MoviesDetailsLoad
import com.example.moviesproject.domain.extentions.toMovieDetailsEntity


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

    override suspend fun loadFreshDetailsData(movieId: Int): MovieDetailsData =
        moviesDetailsLoad.load(movieId)
}