package com.example.moviesproject.domain.use_cases

interface ActorDetailsRepository {
    suspend fun loadActorDetails()
}