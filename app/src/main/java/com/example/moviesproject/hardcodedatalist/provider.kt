package com.example.moviesproject.hardcodedatalist

import com.android.academy.fundamentals.homework.data.MovieRepository

internal interface Provider {
    fun provideMovieRepository(): MovieRepository
}