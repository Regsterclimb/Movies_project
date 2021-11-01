package com.example.moviesproject.data.NetworkModule

import com.example.moviesproject.data.remote.dto.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

class NetworkModule : NetworkModuleGetData {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    private interface MoviesApi {
        @GET("genre/movie/list?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US")
        suspend fun dataLoadGenres(): MovieDataGenres

        @GET("configuration?api_key=29d669e3884b3c81259c3e02780bcec9")
        suspend fun dataLoadConfiguration(): ConfigurationMovieData

        @GET("movie/popular?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US&page=1")
        suspend fun dataLoadMoviesList(): MovieDataPopular

    }

    private interface MovieDetailsApi {
        @GET( "movie/{movieId}?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US)")
        suspend fun loadDataMovieDetailsById(@Path("movieId") id : Int) : MovieDataDetails
        @GET( "movie/{movieId}/credits?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US")
        suspend fun loadDataMovieCastActorsById(@Path("movieId") id : Int) : MovieCastsData
    }

    private interface ActorDetailsApi {

        suspend fun loadDataActorDetailsById()

    }

    private object RetrofitModule {
        private val json = Json {
            ignoreUnknownKeys = true
        }

        private val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()


        @Suppress("EXPERIMENTAL_API_USAGE")
        private val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            //TODO 04: Add okhttp client to retrofit
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        val movieApi: MoviesApi = retrofit.create()
        val movieDetailsApi : MovieDetailsApi = retrofit.create()
    }

    override suspend fun getGenresData(): MovieDataGenres =
        RetrofitModule.movieApi.dataLoadGenres()

    override suspend fun getConfigurationData(): ConfigurationMovieData =
        RetrofitModule.movieApi.dataLoadConfiguration()


    override suspend fun getMoviePopularData() : MovieDataPopular =
        RetrofitModule.movieApi.dataLoadMoviesList()

    override suspend fun getMovieDetailData(id: Int): MovieDataDetails =
        RetrofitModule.movieDetailsApi.loadDataMovieDetailsById(id)

    override suspend fun getCastsActorsData(id: Int): MovieCastsData =
        RetrofitModule.movieDetailsApi.loadDataMovieCastActorsById(id)




}
interface NetworkModuleGetData {

    suspend fun getGenresData() : MovieDataGenres
    suspend fun getConfigurationData() : ConfigurationMovieData
    suspend fun getMoviePopularData() : MovieDataPopular
    suspend fun getMovieDetailData(id: Int) : MovieDataDetails
    suspend fun getCastsActorsData(id: Int) : MovieCastsData

}

