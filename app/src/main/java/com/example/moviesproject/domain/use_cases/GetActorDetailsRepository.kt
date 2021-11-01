package com.example.moviesproject.domain.use_cases

interface GetActorDetailsRepository {

    suspend fun loadActorDetails()

}