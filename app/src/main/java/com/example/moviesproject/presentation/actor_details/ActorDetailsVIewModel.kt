package com.example.moviesproject.presentation.actor_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesproject.domain.model.ActorDetails
import com.example.moviesproject.domain.use_cases.ActorDetailsRepository

class ActorDetailsVIewModel(
    repository: ActorDetailsRepository
) : ViewModel() {
    private val _mutableActorInfo = MutableLiveData<ActorDetails>()

    val actorDetails: LiveData<ActorDetails> = _mutableActorInfo


}