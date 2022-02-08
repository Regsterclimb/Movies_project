package com.example.moviesproject.data.repository

import android.content.Context

interface ActorDetailsRepository {
    suspend fun loadActorDetails()
}

internal class ActorDetailsRepositoryImpl(private val context: Context) : ActorDetailsRepository {

    //loadconfiguration /get from cache or db somthing like that
    //loadActorDetailsFromApi
    //maybeparse it
    //future add list of popular movie with this actor



    override suspend fun loadActorDetails() {
        TODO("Not yet implemented")
    }

}