package com.android.academy.fundamentals.homework.data

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

interface MovieRepository {
    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): Movie?
}

internal class JsonMovieRepository(private val context: Context) : MovieRepository {

    private val jsonFormat = Json { ignoreUnknownKeys = true }

    private var movies: List<Movie>? = null

    override suspend fun loadMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val cachedMovies = movies
        if (cachedMovies != null) {
            cachedMovies
        } else {
            val moviesFromJson = loadMoviesFromJsonFile()
            movies = moviesFromJson
            moviesFromJson
        }
    }

    private suspend fun loadMoviesFromJsonFile(): List<Movie> {
        val genresMap = loadGenres()
        val actorsMap = loadActors()

        Log.d("list2withgenres", "$genresMap")

        val data = readAssetFileToString("data.json")
        return parseMovies(data, genresMap, actorsMap)
    }

    private suspend fun loadGenres(): List<Genre> = withContext(Dispatchers.IO) {
        val list = DataGenreList(genres = RetrofitModule.movieApi.dataLoadGenres().genres)
        list.genres.map { genresItem -> Genre(id = genresItem.id, name = genresItem.name) }

    }

    private fun readAssetFileToString(fileName: String): String {
        val stream = context.assets.open(fileName)
        return stream.bufferedReader().readText()
    }

    private suspend fun loadActors(): List<Actor> = withContext(Dispatchers.IO) {
        val data = readAssetFileToString("people.json")
        val jsonActors = jsonFormat.decodeFromString<List<JsonActor>>(data)

        jsonActors.map { jsonActor ->
            Actor(
                id = jsonActor.id,
                name = jsonActor.name,
                imageUrl = jsonActor.profilePicture
            )
        }
    }

    private fun parseMovies(
        jsonString: String,
        genreData: List<Genre>,
        actors: List<Actor>
    ): List<Movie> {
        Log.d("list1withgenres", "${genreData}")
        val genresMap = genreData.associateBy(Genre::id)
        Log.d("list1withgenres", "${genresMap}")
        val actorsMap = actors.associateBy(Actor::id)

        val jsonMovies = jsonFormat.decodeFromString<List<JsonMovie>>(jsonString)

        return jsonMovies.map { jsonMovie ->
            Log.d("jsonMovie", "${jsonMovie.genreIds}")
            Movie(
                id = jsonMovie.id,
                title = jsonMovie.title,
                storyLine = jsonMovie.overview,
                imageUrl = jsonMovie.posterPicture,
                detailImageUrl = jsonMovie.backdropPicture,
                rating = (jsonMovie.ratings / 2).toInt(),
                reviewCount = jsonMovie.votesCount,
                pgAge = if (jsonMovie.adult) 16 else 13,
                runningTime = jsonMovie.runtime,
                genres = jsonMovie.genreIds.map { id ->
                    genresMap[id].orThrow { IllegalArgumentException("Genre not found") }
                },
                actors = jsonMovie.actors.map { id ->
                    actorsMap[id].orThrow { IllegalArgumentException("Actor not found") }
                },
                isLiked = false
            )
        }
    }

    private interface MoviesApi {
        @GET("genre/movie/list?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US")
        suspend fun dataLoadGenres() : MovieDataGenres
        @GET("configuration?api_key=29d669e3884b3c81259c3e02780bcec9")
        suspend fun dataLoadConfiguration() : List<Images>
        @GET("movie/popular?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US&page=1")
        suspend fun dataLoadMoviesList(): List<ResultsMovie>
    }

    private object RetrofitModule {
        private val json = Json {
            ignoreUnknownKeys = true
        }

        private val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()



        // TODO 01: Instantiate the OkHttpClient val by using ".newBuilder()"
        // TODO 02: Add "HttpLoggingInterceptor" to your Okhttp client
        // TODO 03: Add Logging Level - ".setLevel(HttpLoggingInterceptor.Level.BODY):

        @Suppress("EXPERIMENTAL_API_USAGE")
        private val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            //TODO 04: Add okhttp client to retrofit
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        val movieApi: MoviesApi = retrofit.create()
    }

    override suspend fun loadMovie(movieId: Int): Movie? {
        val cachedMovies = movies ?: loadMovies()
        return cachedMovies.find { it.id == movieId }
    }

    private fun <T : Any> T?.orThrow(createThrowable: () -> Throwable): T {
        return this ?: throw createThrowable()
    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}
