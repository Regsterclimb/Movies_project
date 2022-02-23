package com.example.moviesproject.data.repository.movie_details

import com.example.moviesproject.data.repository.movie_list.MainDataMoviesRepository

interface MoviesDetailsLoad {

    suspend fun load(movieId: Int): MovieDetailsData

    class Base(
        private val mainDataMoviesRep: MainDataMoviesRepository,
        private val parseMovieDetails: ParseMovieDetails,
        private val movieDetailsDataRep: MainMovieDetailsDataRepository
    ) : MoviesDetailsLoad {
        override suspend fun load(
            movieId: Int
        ): MovieDetailsData = parseMovieDetails.parse(
            movieDetailsDataRep.getMovieDetailsApi(movieId),
            mainDataMoviesRep.loadConfigurationApi(),
            movieDetailsDataRep.parseActorsData(
                movieId,
                mainDataMoviesRep.loadConfigurationApi(),
            )
        )

    }
}