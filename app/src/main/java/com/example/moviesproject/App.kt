package com.example.moviesproject

import android.app.Application
import com.example.moviesproject.data.remote.NetworkModuleImpl
import com.example.moviesproject.data.repository.movie_details.MainMovieDetailsDataRepository
import com.example.moviesproject.data.repository.movie_details.MovieDetailsDataRepositoryImpl
import com.example.moviesproject.data.repository.movie_details.ParseMovieDetails
import com.example.moviesproject.data.repository.movie_list.MainDataMoviesRepository
import com.example.moviesproject.data.repository.movie_list.MovieDataRepositoryImpl
import com.example.moviesproject.data.repository.movie_list.MoviesDataList
import com.example.moviesproject.data.repository.movie_list.ParseMovieData
import com.example.moviesproject.domain.repository.movie_details.MovieDetailsRepositoryImpl
import com.example.moviesproject.domain.repository.movie_list.MovieRepositoryImpl
import com.example.moviesproject.domain.use_cases.MovieDetailsCatch
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase
import com.example.moviesproject.domain.use_cases.MovieListCatch
import com.example.moviesproject.domain.use_cases.MoviesListUseCase

class App : Application() {

    val movieDataUseCase by lazy {
        MoviesListUseCase(
            MovieRepositoryImpl(
                MovieDataRepositoryImpl(
                    this,
                    ParseMovieData.Base(),
                    MainDataMoviesRepository.Base(NetworkModuleImpl()),
                    MoviesDataList.Base()
                )
            ),
            MovieListCatch.Base()
        )
    }

    val movieDetailsUseCase by lazy {
        MovieDetailsUseCase(
            MovieDetailsRepositoryImpl(
                MovieDetailsDataRepositoryImpl(
                    this,
                    MainDataMoviesRepository.Base(NetworkModuleImpl()),
                    ParseMovieDetails.Base(),
                    MainMovieDetailsDataRepository.Base(
                        MainDataMoviesRepository.Base(NetworkModuleImpl()),
                        NetworkModuleImpl()
                    )
                )
            ),
            MovieDetailsCatch.Base()
        )
    }
}