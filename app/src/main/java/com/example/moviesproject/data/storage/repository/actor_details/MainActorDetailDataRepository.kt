package com.example.moviesproject.data.storage.repository.actor_details

import com.example.moviesproject.data.model.ActorDetailsData
import com.example.moviesproject.data.remote.network_module.NetworkModule
import com.example.moviesproject.data.storage.extentions.toActorDetailsData

interface MainActorDetailDataRepository {

    suspend fun loadActor(actorId: Int): ActorDetailsData

    class Base(private val networkModule: NetworkModule) : MainActorDetailDataRepository {

        override suspend fun loadActor(actorId: Int): ActorDetailsData =
            networkModule.getActorDetailsData(actorId).toActorDetailsData()
    }
}