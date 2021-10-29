package com.example.moviesproject.data

interface MovieDetailsRepository {
    suspend fun loadMovie()
}