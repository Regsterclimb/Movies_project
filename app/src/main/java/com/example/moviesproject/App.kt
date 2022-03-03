package com.example.moviesproject

import android.app.Application
import com.example.moviesproject.data.remote.network_module.NetworkModule
import com.example.moviesproject.data.repository.movie_details.MovieDetailsDataRepositoryImpl
import com.example.moviesproject.data.repository.movie_details.MovieDetailsRepositoryImpl
import com.example.moviesproject.data.repository.movie_list.MovieRepositoryImpl
import com.example.moviesproject.data.storage.repository.movie_details.MainMovieDetailsDataRepository
import com.example.moviesproject.data.storage.repository.movie_details.MoviesDetailsLoad
import com.example.moviesproject.data.storage.repository.movie_details.ParseMovieDetails
import com.example.moviesproject.data.storage.repository.movie_list.MainDataMoviesRepository
import com.example.moviesproject.data.storage.repository.movie_list.MovieDataRepositoryImpl
import com.example.moviesproject.data.storage.repository.movie_list.MoviesDataList
import com.example.moviesproject.data.storage.repository.movie_list.ParseMovieData
import com.example.moviesproject.domain.use_cases.logic.MovieDetailsLogic
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase
import com.example.moviesproject.domain.use_cases.logic.MovieListLogic
import com.example.moviesproject.domain.use_cases.MoviesListUseCase

class App : Application() {

    val movieDataUseCase by lazy {
        MoviesListUseCase(
            MovieRepositoryImpl(
                MovieDataRepositoryImpl(
                    this,
                    MoviesDataList.Base(
                        MainDataMoviesRepository.Base(
                            NetworkModule()
                        ),
                        ParseMovieData.Base()
                    )
                )
            ),
            MovieListLogic.Base()
        )
    }

    val movieDetailsUseCase by lazy {
        MovieDetailsUseCase(
            MovieDetailsRepositoryImpl(
                MovieDetailsDataRepositoryImpl(
                    this,
                    MoviesDetailsLoad.Base(
                        MainDataMoviesRepository.Base(NetworkModule()),
                        ParseMovieDetails.Base(),
                        MainMovieDetailsDataRepository.Base(
                            MainDataMoviesRepository.Base(NetworkModule()),
                            NetworkModule()
                        )
                    )
                )
            ),
            MovieDetailsLogic.Base()
        )
    }
}