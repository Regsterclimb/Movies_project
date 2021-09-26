package com.example.moviesproject.avengers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesproject.R
import com.example.moviesproject.data.Actor
import com.example.moviesproject.hardcodedatalist.ActorsDataSource

class ActorAdapter : RecyclerView.Adapter<ActorDataViewHolder>() {

    private var actors = ActorsDataSource().getActors()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorDataViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_actor, parent, false)
        return ActorDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorDataViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    fun bindActors(newActor: List<Actor>) {
        actors = newActor
    }


}