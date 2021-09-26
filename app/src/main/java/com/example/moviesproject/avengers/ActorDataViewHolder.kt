package com.example.moviesproject.avengers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesproject.R
import com.example.moviesproject.data.Actor

class ActorDataViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private var avatar : ImageView? = itemView.findViewById(R.id.avatar_actor)
    private var actorName : TextView? = itemView.findViewById(R.id.actor_name)

    fun onBind(actor:Actor) {
        avatar?.setImageResource(actor.avatar)
        actorName?.text = actor.name

    }

}