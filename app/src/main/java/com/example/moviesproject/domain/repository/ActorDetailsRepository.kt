package com.example.moviesproject.domain.repository

import android.content.Context

interface GetActorDetailsRepository {
    suspend fun loadActorDetails()
}

internal class ActorDetailsRepository(private val context: Context) : GetActorDetailsRepository {

    //loadconfiguration /get from cache or db somthing like that
    //loadActorDetailsFromApi
    //maybeparse it
    //future add list of popular movie with this actor



    override suspend fun loadActorDetails() {
        TODO("Not yet implemented")
    }

}