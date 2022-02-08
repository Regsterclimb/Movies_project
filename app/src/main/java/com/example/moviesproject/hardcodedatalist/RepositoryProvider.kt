package com.example.moviesproject.hardcodedatalist

import com.example.moviesproject.domain.use_cases.ActorDetailsRepository
import com.example.moviesproject.domain.use_cases.MovieDetailsRepository
import com.example.moviesproject.domain.use_cases.MovieRepository

interface RepositoryProvider {

    fun provideMovieRepository(): MovieRepository
    fun provideMovieDetailsRepository(): MovieDetailsRepository
    fun provideActorDetailsRepository(): ActorDetailsRepository
}