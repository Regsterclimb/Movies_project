package com.example.moviesproject.avengers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesproject.R
import com.example.moviesproject.data.Movie

class MovieDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val hashTags: TextView? = itemView.findViewById(R.id.tags)
    private val reViewsCount: TextView? = itemView.findViewById(R.id.fragment_review)
    private val movieTitle: TextView? = itemView.findViewById(R.id.avengers_title)
    private val movieDuration: TextView? = itemView.findViewById(R.id.movie_duration)
    private val topImage: ImageView? = itemView.findViewById(R.id.avengers_icon)

    fun onBind(movie:Movie) {
        hashTags?.text = movie.hashTags
        reViewsCount?.text = movie.reViewsCount
        movieTitle?.text = movie.movieTitle
        movieDuration?.text = movie.movieDuration
        topImage?.setImageResource(movie.topImage)

    }


}