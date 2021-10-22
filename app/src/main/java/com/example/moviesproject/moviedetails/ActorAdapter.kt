package com.example.moviesproject.moviedetails

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesproject.R
import com.example.moviesproject.data.Actor

class ActorAdapter : RecyclerView.Adapter<ActorDataViewHolder>() {

    private var actors = listOf<Actor>()

    fun getActors() : List<Actor> = actors

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorDataViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_actor, parent, false)
        return ActorDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorDataViewHolder, position: Int) {
        holder.onBind(actors[position])
        Log.d("listactor2", "$actors")
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    fun bindActors(newActor: List<Actor>) {
        actors = newActor
        Log.d("listactor1", "${actors}")

    }


}