package com.example.moviesproject.data.storage.repository.movie_list

import com.example.moviesproject.data.remote.network_module.NetworkModule
import com.example.moviesproject.data.remote.respones.GenreResponse
import com.example.moviesproject.data.remote.respones.ImagesResponse
import com.example.moviesproject.data.remote.respones.MovieCastsResponse
import com.example.moviesproject.domain.extentions.toMoviePopular
import com.example.moviesproject.domain.model.MoviePopular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MainDataMoviesRepository {

    suspend fun loadMoviesApi(): MoviePopular
    suspend fun loadGenresApi(): List<GenreResponse>
    suspend fun loadConfigurationApi(): ImagesResponse
    suspend fun getActorsDataFromApi(movieId: Int): MovieCastsResponse

    class Base(private val networkModule: NetworkModule) : MainDataMoviesRepository {
        override suspend fun loadMoviesApi(): MoviePopular = withContext(Dispatchers.IO) {
            networkModule.getMoviePopularData().toMoviePopular()
        }

        override suspend fun loadGenresApi(): List<GenreResponse> = withContext(Dispatchers.IO) {
            networkModule.getGenresData().genreResponses
        }

        override suspend fun loadConfigurationApi(): ImagesResponse = withContext(Dispatchers.IO) {
            networkModule.getConfigurationData().images
        }

        override suspend fun getActorsDataFromApi(movieId: Int): MovieCastsResponse =
            withContext(Dispatchers.IO) {
                networkModule.getCastsActorsData(movieId)
            }
    }
}
