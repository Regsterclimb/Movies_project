package com.example.moviesproject.presentation.movie_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesproject.R
import com.example.moviesproject.data.remote.respones.ActorResponse

class ActorAdapter(private val onItemListener: (actorResponse: ActorResponse) -> Unit) :
    ListAdapter<ActorResponse, ActorDataViewHolder>(ActorsCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorDataViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_actor, parent, false)
        return ActorDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorDataViewHolder, position: Int) {
        holder.onBind(getItem(position), onItemListener)
    }
}

