package com.example.moviesproject.data.remote.network_module

import com.example.moviesproject.data.remote.respones.ActorByIdResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ActorDetailsApi {

    @GET("person/{person_id}?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US")
    suspend fun loadActorDetails(@Path("person_id") actorId: Int) : ActorByIdResponse

}