package com.example.moviesproject.movielist

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesproject.R
import com.example.moviesproject.data.Movie

class MovieAdapter(private val onItemListner: (movie: Movie) -> Unit) :
    ListAdapter<Movie, MovieDataViewHolder>(MovieListCallBack()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDataViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        return MovieDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieDataViewHolder, position: Int) {

        holder.onBind(getItem(position), onItemListner)

    }


}

class MovieDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val hashTags: TextView? = itemView.findViewById(R.id.tags)
    private val reViewsCount: TextView? = itemView.findViewById(R.id.fragment_review)
    private val movieTitle: TextView? = itemView.findViewById(R.id.avengers_title)
    private val movieDuration: TextView? = itemView.findViewById(R.id.movie_duration)
    private val topImage: ImageView = itemView.findViewById(R.id.avengers_icon)
    private val stars: List<ImageView?> = listOf(
        itemView.findViewById(R.id.fragment_star1),
        itemView.findViewById(R.id.fragment_star2),
        itemView.findViewById(R.id.fragment_star3),
        itemView.findViewById(R.id.fragment_star4),
        itemView.findViewById(R.id.fragment_star5)
    )

    @SuppressLint("ResourceAsColor")
    fun onBind(movie: Movie, onItemClicked: (movie: Movie) -> Unit) {
        hashTags?.text = movie.genres.toString()
        reViewsCount?.text = movie.reviewCount.toString()
        movieTitle?.text = movie.title
        movieDuration?.text = movie.runningTime.toString()
        Glide.with(itemView)
            .load(movie.imageUrl)
            .centerCrop()
            .into(topImage)

        Log.d("color_if", "${stars }}")

        stars.forEachIndexed { index, imageView ->
            if (index < movie.rating) {
                imageView?.setImageResource(R.drawable.ic_star_icon)
                Log.d("color_if", " movie id ${movie.id} + index ${stars}" )


            } else {
                imageView?.setImageResource(R.drawable.star_icon_gray)
                Log.d("color_else", "movie id ${movie.id} + index $index" )
            }
        }

        itemView.setOnClickListener {
            onItemClicked(movie)
        }



    }
}

class MovieListCallBack : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}