package com.example.moviesproject.data.di

import com.example.moviesproject.data.model.ActorDetailsData

interface ActorDetailsDataRepository {
    suspend fun loadActorData(actorId : Int) : ActorDetailsData
}