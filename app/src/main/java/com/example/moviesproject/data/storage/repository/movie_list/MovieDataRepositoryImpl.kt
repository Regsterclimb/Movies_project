package com.example.moviesproject.data.storage.repository.movie_list

import android.content.Context
import com.example.moviesproject.data.di.MovieDataRepository
import com.example.moviesproject.data.model.MovieData
import com.example.moviesproject.data.storage.extentions.toMovieData
import com.example.moviesproject.data.storage.extentions.toMovieEntity
import com.example.moviesproject.data.storage.movie_list.DataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataRepositoryImpl(
    appContext: Context,
    private val movieDataList: MoviesDataList
) : MovieDataRepository {

    private var dataBase = DataBase.create(appContext).moviesDao()

    override suspend fun getMoviesDataList(): List<MovieData> = withContext(Dispatchers.IO) {
        dataBase.getAllMovies().map { movieEntity ->
            movieEntity.toMovieData()
        }
            .ifEmpty {
                val list = getFreshMovieDataList()
                saveMoviesListDb(list)
                list
            }
    }

    override suspend fun getFreshMovieDataList(): List<MovieData> = withContext(Dispatchers.IO) {
        val list = movieDataList.load()
        saveMoviesListDb(list)
        list
    }

    override suspend fun saveMoviesListDb(list: List<MovieData>) = withContext(Dispatchers.Default) {
        dataBase.insertAllMovies(
            list.map { movie ->
                movie.toMovieEntity()
            }
        )
    }
}
