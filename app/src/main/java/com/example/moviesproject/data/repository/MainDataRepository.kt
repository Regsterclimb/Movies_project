package com.example.moviesproject.data.repository

import com.example.moviesproject.data.remote.NetworkModule.NetworkModuleImpl
import com.example.moviesproject.data.respones.GenreResponse
import com.example.moviesproject.data.respones.ImagesResponse
import com.example.moviesproject.domain.model.MoviePopular
import com.example.moviesproject.domain.model.toMoviePopular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MainDataRepository {

    suspend fun loadMoviesApi(networkModule: NetworkModuleImpl): MoviePopular
    suspend fun loadGenresApi(networkModuleImpl: NetworkModuleImpl): List<GenreResponse>
    suspend fun loadConfigurationFromApi(networkModuleImpl: NetworkModuleImpl): ImagesResponse

    class Base : MainDataRepository {

        override suspend fun loadMoviesApi(networkModule: NetworkModuleImpl): MoviePopular =
            withContext(Dispatchers.IO) {
                networkModule.getMoviePopularData().toMoviePopular()
            }

        override suspend fun loadGenresApi(networkModuleImpl: NetworkModuleImpl): List<GenreResponse> =
            withContext(Dispatchers.IO) {
                networkModuleImpl.getGenresData().genreResponses.map { genresItem ->
                    GenreResponse(
                        id = genresItem.id,
                        name = genresItem.name
                    )
                }
            }

        override suspend fun loadConfigurationFromApi(networkModuleImpl: NetworkModuleImpl): ImagesResponse =
            withContext(Dispatchers.IO) {
                networkModuleImpl.getConfigurationData().images
            }
    }
}
