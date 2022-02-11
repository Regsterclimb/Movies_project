package com.example.moviesproject.presentation.movie_details

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesproject.data.remote.respones.ActorResponse

class ActorsCallBack : DiffUtil.ItemCallback<ActorResponse>() {

    override fun areItemsTheSame(oldItem: ActorResponse, newItem: ActorResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ActorResponse, newItem: ActorResponse): Boolean {
        return oldItem == newItem
    }
}