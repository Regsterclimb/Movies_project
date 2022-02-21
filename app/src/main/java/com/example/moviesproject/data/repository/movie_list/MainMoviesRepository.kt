package com.example.moviesproject.data.repository.movie_list

import com.example.moviesproject.data.remote.NetworkModuleImpl
import com.example.moviesproject.data.remote.respones.GenreResponse
import com.example.moviesproject.data.remote.respones.ImagesResponse
import com.example.moviesproject.data.remote.respones.MovieCastsResponse
import com.example.moviesproject.domain.model.MoviePopular
import com.example.moviesproject.domain.model.toMoviePopular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MainMoviesRepository {

    suspend fun loadMoviesApi(networkModule: NetworkModuleImpl): MoviePopular
    suspend fun loadGenresApi(networkModuleImpl: NetworkModuleImpl): List<GenreResponse>
    suspend fun loadConfigurationFromApi(networkModuleImpl: NetworkModuleImpl): ImagesResponse
    suspend fun getActorsDataFromApi(
        movieId: Int,
        networkModuleImpl: NetworkModuleImpl
    ): MovieCastsResponse

    class Base : MainMoviesRepository {
        override suspend fun loadMoviesApi(networkModule: NetworkModuleImpl): MoviePopular =
            withContext(Dispatchers.IO) {
                networkModule.getMoviePopularData().toMoviePopular()
            }

        override suspend fun loadGenresApi(networkModuleImpl: NetworkModuleImpl): List<GenreResponse> =
            withContext(Dispatchers.IO) {
                networkModuleImpl.getGenresData().genreResponses
            }

        override suspend fun loadConfigurationFromApi(networkModuleImpl: NetworkModuleImpl): ImagesResponse =
            withContext(Dispatchers.IO) {
                networkModuleImpl.getConfigurationData().images
            }

        override suspend fun getActorsDataFromApi(
            movieId: Int,
            networkModuleImpl: NetworkModuleImpl
        ): MovieCastsResponse =
            withContext(Dispatchers.IO) {
                networkModuleImpl.getCastsActorsData(id = movieId)
            }
    }
}
