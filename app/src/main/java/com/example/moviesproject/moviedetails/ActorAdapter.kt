package com.example.moviesproject.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesproject.R
import com.example.moviesproject.data.Actor

class ActorAdapter : ListAdapter<Actor, ActorDataViewHolder>(ActorsCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorDataViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_actor, parent, false)
        return ActorDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorDataViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


}

class ActorsCallBack : DiffUtil.ItemCallback<Actor>() {

    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem == newItem
    }

}