package com.example.moviesproject.presentation.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesproject.R
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.presentation.movie_list.support.StarsColor

class MovieAdapter(private val onItemListener: (movie: Movie) -> Unit) :
    ListAdapter<Movie, MovieDataViewHolder>(MovieListCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDataViewHolder =
        MovieDataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        )

    override fun onBindViewHolder(holder: MovieDataViewHolder, position: Int) =
        holder.onBind(getItem(position), onItemListener, StarsColor.Base())
}

class MovieListCallBack : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}