package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.ActorDetails
import com.example.moviesproject.domain.repository.actor_details.ActorDetailsRepository

class ActorDetailsUseCase(
    private val actorDetailsRepository: ActorDetailsRepository
) {
    suspend fun loadActorDetails(actorId: Int): ActorDetails =
        actorDetailsRepository.loadActorDetails(actorId)
}