package com.example.moviesproject.domain.repository

interface ActorDetailsRepository {
    suspend fun loadActorDetails()
}

class ActorDetailsRepositoryFromApi : ActorDetailsRepository {

    //loadconfiguration /get from cache or db somthing like that
    //loadActorDetailsFromApi
    //maybeparse it
    //future add list of popular movie with this actor



    override suspend fun loadActorDetails() {
        TODO("Not yet implemented")
    }

}