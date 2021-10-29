package com.example.moviesproject.hardcodedatalist

import com.android.academy.fundamentals.homework.data.MovieRepository
import com.example.moviesproject.data.MovieDetailsRepository

interface RepositoryProvider {

    fun provideMovieRepository(): MovieRepository
    fun provideMovieDetailsRepository() : MovieDetailsRepository
}