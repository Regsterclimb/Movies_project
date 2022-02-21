package com.example.moviesproject.presentation.movie_details

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.moviesproject.data.remote.respones.ActorResponse
import com.example.moviesproject.databinding.ViewHolderActorBinding

class ActorDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(ViewHolderActorBinding::bind)

    fun onBind(
        actorResponse: ActorResponse,
        clickOnItemListener: (actorResponse: ActorResponse) -> Unit
    ) {
        with(viewBinding) {
            actorAvatar.load(actorResponse.imageUrl)
            actorName.text = actorResponse.name
        }
        itemView.setOnClickListener {
            clickOnItemListener(actorResponse)
        }
    }
}