package com.example.moviesproject.data.storage.repository.actor_details

import com.example.moviesproject.data.remote.network_module.NetworkModule
import com.example.moviesproject.data.remote.respones.ActorByIdResponse

interface MainActorDetailDataRepository {

    suspend fun loadActor(actorId: Int): ActorByIdResponse

    class Base(private val networkModule: NetworkModule) : MainActorDetailDataRepository {

        override suspend fun loadActor(actorId: Int): ActorByIdResponse =
            networkModule.getActorDetailsData(actorId)
    }
}