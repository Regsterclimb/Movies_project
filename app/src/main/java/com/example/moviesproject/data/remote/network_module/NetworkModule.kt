package com.example.moviesproject.data.remote.network_module

import com.example.moviesproject.data.remote.respones.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create


class NetworkModule : NetworkModuleResponses {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    private object RetrofitModule {

        private val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        private val httpClient = OkHttpClient().newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()

        private val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        fun getMoviesData(): MoviesApi = retrofit.create()
        fun getMoviesDetailsData(): MovieDetailsApi = retrofit.create()
        fun getActorDetailsData(): ActorDetailsApi = retrofit.create()
    }

    override suspend fun getGenresData(): MovieGenresResponse =
        RetrofitModule.getMoviesData().loadDataGenres()

    override suspend fun getConfigurationData(): MovieConfigurationResponse =
        RetrofitModule.getMoviesData().loadDataConfiguration()

    override suspend fun getMoviePopularData(): MoviePopularResponse =
        RetrofitModule.getMoviesData().loadDataMoviesList()

    override suspend fun getMovieDetailData(id: Int): MovieDetailsResponse =
        RetrofitModule.getMoviesDetailsData().loadDataMovieDetailsById(id)

    override suspend fun getCastsActorsData(id: Int): MovieCastsResponse =
        RetrofitModule.getMoviesDetailsData().loadDataMovieCastActorsById(id)

    override suspend fun getActorDetailsData(actorId: Int): ActorByIdResponse =
        RetrofitModule.getActorDetailsData().loadActorDetails(actorId)
            //asdasdasdasdasdasd

}

