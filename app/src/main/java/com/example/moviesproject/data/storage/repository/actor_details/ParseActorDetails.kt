package com.example.moviesproject.data.storage.repository.actor_details

import com.example.moviesproject.data.model.ActorDetailsData
import com.example.moviesproject.data.remote.respones.ActorByIdResponse
import com.example.moviesproject.data.storage.extentions.toActorDetailsData
import com.example.moviesproject.data.storage.repository.movie_list.MainDataMoviesRepository

interface ParseActorDetails {

    suspend fun parseActor(actorDetails: ActorByIdResponse): ActorDetailsData

    class Base(
        private val mainDataMoviesRepository: MainDataMoviesRepository,
    ) : ParseActorDetails {

        override suspend fun parseActor(actorDetails: ActorByIdResponse): ActorDetailsData =
            ActorByIdResponse(
                actorDetails.alsoKnownAs,
                actorDetails.birthday ?: "",
                actorDetails.gender,
                actorDetails.imdbId,
                actorDetails.knownForDepartment,
                posterUrlPath = with(mainDataMoviesRepository.loadConfigurationApi()) {
                    baseUrl + posterSizes[4]
                } + actorDetails.posterUrlPath,
                actorDetails.biography,
                actorDetails.deathDay ?: "",
                actorDetails.placeOfBirth ?: "",
                actorDetails.popularity,
                actorDetails.name,
                actorDetails.id
            ).toActorDetailsData()
    }
}