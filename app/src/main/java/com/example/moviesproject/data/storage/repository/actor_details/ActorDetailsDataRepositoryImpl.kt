package com.example.moviesproject.data.storage.repository.actor_details

import android.content.Context
import com.example.moviesproject.data.di.ActorDetailsDataRepository
import com.example.moviesproject.data.model.ActorDetailsData

class ActorDetailsDataRepositoryImpl(
    context: Context,
    private val parseActorDetails: ParseActorDetails,
    private val mainActorDetailDataRepository: MainActorDetailDataRepository
) : ActorDetailsDataRepository {

    override suspend fun loadActorData(actorId: Int): ActorDetailsData =
        parseActorDetails.parseActor(mainActorDetailDataRepository.loadActor(actorId))
}
