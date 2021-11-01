package com.example.moviesproject.hardcodedatalist

import com.example.moviesproject.domain.use_cases.GetActorDetailsRepository
import com.example.moviesproject.domain.use_cases.GetMovieDetailsRepository
import com.example.moviesproject.domain.use_cases.GetMovieRepository

interface RepositoryProvider {

    fun provideMovieRepository(): GetMovieRepository
    fun provideMovieDetailsRepository() : GetMovieDetailsRepository
    fun provideActorDetailsRepository() : GetActorDetailsRepository
}