package com.example.moviesproject.presentation.actor_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesproject.domain.model.ActorDetails
import com.example.moviesproject.domain.use_cases.ActorDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActorDetailsViewModel(
    private val actorDetailsUseCase: ActorDetailsUseCase
) : ViewModel() {
    private val _mutableActorInfo = MutableLiveData<ActorDetails>()

    val actorDetails: LiveData<ActorDetails> = _mutableActorInfo

    fun loadActorDetails(actorId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _mutableActorInfo.value = actorDetailsUseCase.loadActorDetails(actorId)
        }
    }
}