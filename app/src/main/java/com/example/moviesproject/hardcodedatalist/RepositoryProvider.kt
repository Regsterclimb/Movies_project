package com.example.moviesproject.hardcodedatalist

import com.android.academy.fundamentals.homework.data.MovieRepository

interface RepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}