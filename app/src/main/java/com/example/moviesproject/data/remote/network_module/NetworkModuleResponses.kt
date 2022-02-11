package com.example.moviesproject.data.remote.network_module

import com.example.moviesproject.data.remote.respones.*

interface NetworkModuleResponses {

    suspend fun getGenresData(): MovieGenresResponse
    suspend fun getConfigurationData(): MovieConfigurationResponse
    suspend fun getMoviePopularData(): MoviePopularResponse
    suspend fun getMovieDetailData(id: Int): MovieDetailsResponse
    suspend fun getCastsActorsData(id: Int): MovieCastsResponse

}