package com.example.moviesproject.data.repository.movie_list

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.data_base.extentions.toMovieData
import com.example.moviesproject.data.data_base.extentions.toMovieEntity
import com.example.moviesproject.data.data_base.movie_list.DataBase
import com.example.moviesproject.data.di.MovieDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataRepositoryImpl(
    appContext: Context,
    private val parseMovieData: ParseMovieData,
    private val mainDataMoviesRepository: MainDataMoviesRepository,
    private val movieDataList: MoviesDataList
) : MovieDataRepository {

    private var dataBase = DataBase.create(appContext).moviesDao()


    override suspend fun getMoviesDataList(): List<MovieData> = withContext(Dispatchers.IO) {
        dataBase.getAllMovies().map { movieEntity ->
            movieEntity.toMovieData()
        }
            .ifEmpty {
                val list = movieDataList.load(mainDataMoviesRepository, parseMovieData)
                dataBase.insertAllMovies(
                    list.map { movie ->
                        movie.toMovieEntity()
                    }
                )
                list
            }
    }

    override suspend fun getRefreshedList(): List<MovieData> = withContext(Dispatchers.IO) {
        movieDataList.load(mainDataMoviesRepository, parseMovieData)
    }

    init {
        Log.d("initStart", "MovieDataRepositoryImpl")
    }

    protected fun finalize() {
        Log.d("initStart", "MovieDataRepositoryImpl finalize")
    }
}
