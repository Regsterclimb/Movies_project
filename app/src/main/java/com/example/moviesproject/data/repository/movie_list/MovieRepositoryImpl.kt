package com.example.moviesproject.data.repository.movie_list

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.data_base.DataBase
import com.example.moviesproject.data.data_base.entity.toMovie
import com.example.moviesproject.data.remote.NetworkModuleImpl
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.model.toMovieEntity
import com.example.moviesproject.domain.use_cases.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    appContext: Context,
    private val parseMovie: ParseMovie,
    private val mainMoviesRepository: MainMoviesRepository,
    private val networkModule: NetworkModuleImpl
) : MovieRepository {

    private var dataBase = DataBase.create(appContext).moviesDao()

    init {
        Log.d("init", "MovieRepositoryImpl")
    }

    override suspend fun loadMovies(): List<Movie> = withContext(Dispatchers.IO) {
        dataBase.getAllMovies().map { movieEntity ->
            movieEntity.toMovie()
        }
            .ifEmpty {
                val list = loadMovieList()
                dataBase.insertAllMovies(
                    list.map { movie ->
                        movie.toMovieEntity()
                    }
                )
                list
            }
    }

    private suspend fun loadMovieList(): List<Movie> = parseMovie.parse(
        mainMoviesRepository.loadMoviesApi(networkModule).results,
        mainMoviesRepository.loadGenresApi(networkModule),
        mainMoviesRepository.loadConfigurationFromApi(networkModule)
    )
}
