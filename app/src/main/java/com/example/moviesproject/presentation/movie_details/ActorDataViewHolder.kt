package com.example.moviesproject.presentation.movie_details

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesproject.R
import com.example.moviesproject.data.remote.dto.CastActor

class ActorDataViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private var avatar : ImageView = itemView.findViewById(R.id.avatar_actor)
    private var actorName : TextView = itemView.findViewById(R.id.actor_name)

    fun onBind(actor: CastActor, clickOnItemListner : (actor : CastActor) -> Unit) {
       Glide
           .with(itemView)
           .load(actor.imageUrl)
           .into(avatar)

        actorName.text = actor.name
        itemView.setOnClickListener { clickOnItemListner(actor) }


    }

}