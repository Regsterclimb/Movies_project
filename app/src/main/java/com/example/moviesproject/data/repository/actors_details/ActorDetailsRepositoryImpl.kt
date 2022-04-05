package com.example.moviesproject.data.repository.actors_details

import com.example.moviesproject.data.di.ActorDetailsDataRepository
import com.example.moviesproject.domain.extentions.toActorsDetails
import com.example.moviesproject.domain.model.ActorDetails
import com.example.moviesproject.domain.repository.actor_details.ActorDetailsRepository

class ActorDetailsRepositoryImpl(
    private val actorDetailsDataRepository: ActorDetailsDataRepository
) : ActorDetailsRepository {

    override suspend fun loadActorDetails(actorId: Int): ActorDetails =
        actorDetailsDataRepository.loadActorData(actorId).toActorsDetails()
}