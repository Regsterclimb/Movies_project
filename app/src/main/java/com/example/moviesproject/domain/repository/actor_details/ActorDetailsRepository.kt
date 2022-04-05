package com.example.moviesproject.domain.repository.actor_details

import com.example.moviesproject.domain.model.ActorDetails

interface ActorDetailsRepository {
    suspend fun loadActorDetails(actorId:Int) : ActorDetails
}