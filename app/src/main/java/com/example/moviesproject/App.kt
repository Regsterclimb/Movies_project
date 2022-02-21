package com.example.moviesproject

import android.app.Application
import com.example.moviesproject.data.remote.NetworkModuleImpl
import com.example.moviesproject.data.repository.movie_details.MainMovieDetailsRepository
import com.example.moviesproject.data.repository.movie_details.MovieDetailsDataRepositoryImpl
import com.example.moviesproject.data.repository.movie_details.ParseMovieDetails
import com.example.moviesproject.data.repository.movie_list.MainMoviesRepository
import com.example.moviesproject.data.repository.movie_list.MovieRepositoryImpl
import com.example.moviesproject.data.repository.movie_list.ParseMovie

class App : Application() {
    val repository by lazy {
        MovieRepositoryImpl(
            this, ParseMovie.Base(), MainMoviesRepository.Base(), NetworkModuleImpl()
        )
    }
    val repositoryDetails by lazy {
        MovieDetailsDataRepositoryImpl(
            this,
            MainMoviesRepository.Base(),
            ParseMovieDetails.Base(),
            NetworkModuleImpl(),
            MainMovieDetailsRepository.Base(
                MainMoviesRepository.Base()
            )
        )
    }
}