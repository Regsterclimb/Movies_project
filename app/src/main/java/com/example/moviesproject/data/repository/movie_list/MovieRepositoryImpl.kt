package com.example.moviesproject.data.repository.movie_list

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.data_base.extentions.toMovieData
import com.example.moviesproject.data.data_base.movies.DataBase
import com.example.moviesproject.domain.extentions.toMovieEntity
import com.example.moviesproject.domain.use_cases.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    appContext: Context,
    private val parseMovie: ParseMovie,
    private val mainMoviesRepository: MainMoviesRepository,
    private val movieList: MoviesList
) : MovieRepository {

    private var dataBase = DataBase.create(appContext).moviesDao()

    init {
        Log.d("init", "MovieRepositoryImpl")
    }

    override suspend fun getMoviesList(): List<MovieData> = withContext(Dispatchers.IO) {
        dataBase.getAllMovies().map { movieEntity ->
            movieEntity.toMovieData()
        }
            .ifEmpty {
                val list = movieList.load(mainMoviesRepository, parseMovie)
                dataBase.insertAllMovies(
                    list.map { movie ->
                        movie.toMovieEntity()
                    }
                )
                list
            }
    }
}
