package com.example.moviesproject.data.remote.NetworkModule

import com.example.moviesproject.data.respones.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

class NetworkModule : NetworkModuleResponses {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    private interface MoviesApi {
        @GET("genre/movie/list?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US") // movie genres
        suspend fun loadDataGenres(): MovieGenresResponse

        @GET("configuration?api_key=29d669e3884b3c81259c3e02780bcec9") // configuration movie (images urs, image sizes...)
        suspend fun loadDataConfiguration(): MovieConfigurationResponse

        @GET("movie/popular?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US&page=1")
        suspend fun loadDataMoviesList(): MoviePopularResponse // all data about pupular movie

    }

    private interface MovieDetailsApi {
        @GET("movie/{movieId}?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US)")
        suspend fun loadDataMovieDetailsById(
            @Path("movieId") id: Int
        ): MovieDetailsResponse // all data details from Api

        @GET("movie/{movieId}/credits?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US")
        suspend fun loadDataMovieCastActorsById(
            @Path("movieId") id: Int
        ): MovieCastsResponse // actors info (avatar images url, names , characters ...)
    }

    private interface ActorDetailsApi {

        suspend fun loadDataActorDetailsById()

    }

    private object RetrofitModule {

        private val json = Json {
            ignoreUnknownKeys = true
        }

        private val httpClient = OkHttpClient().newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY) // simple interceptor for logs
            )
            .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        fun getMoviesData(): MoviesApi = retrofit.create()
        fun getMoviesDetailsData(): MovieDetailsApi = retrofit.create()
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


}

interface NetworkModuleResponses {

    suspend fun getGenresData(): MovieGenresResponse
    suspend fun getConfigurationData(): MovieConfigurationResponse
    suspend fun getMoviePopularData(): MoviePopularResponse
    suspend fun getMovieDetailData(id: Int): MovieDetailsResponse
    suspend fun getCastsActorsData(id: Int): MovieCastsResponse

}

