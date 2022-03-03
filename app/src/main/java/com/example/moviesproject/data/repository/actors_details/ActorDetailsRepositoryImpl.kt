package com.example.moviesproject.data.repository

import android.content.Context
import android.util.Log

interface ActorDetailsRepository {
    suspend fun loadActorDetails()
}

internal class ActorDetailsRepositoryImpl(private val context: Context) : ActorDetailsRepository {

    //loadconfiguration /get from cache or db somthing like that
    //loadActorDetailsFromApi
    //maybeparse it
    //future add list of popular movie with this actor

    init {
        Log.d("init", "ActorsDetailsRep")
    }

    override suspend fun loadActorDetails() {
        TODO("Not yet implemented")
    }

}