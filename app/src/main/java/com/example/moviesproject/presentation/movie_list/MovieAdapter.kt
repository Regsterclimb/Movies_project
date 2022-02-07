package com.example.moviesproject.presentation.movie_list

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
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.moviesproject.R
import com.example.moviesproject.databinding.ViewHolderMovieBinding
import com.example.moviesproject.domain.model.Movie

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

    private val viewBinding by viewBinding(ViewHolderMovieBinding::bind)

    private val movieTitle: TextView? = itemView.findViewById(R.id.listTitle)
    private val movieDuration: TextView? = itemView.findViewById(R.id.listDuraction)
    private val topImage: ImageView = itemView.findViewById(R.id.listImage)
    private val stars: List<ImageView?> = listOf(
        itemView.findViewById(R.id.listStar1),
        itemView.findViewById(R.id.listStar2),
        itemView.findViewById(R.id.listStar3),
        itemView.findViewById(R.id.listStar4),
        itemView.findViewById(R.id.listStar5)
    )

    @SuppressLint("ResourceAsColor")
    fun onBind(movie: Movie, onItemClicked: (movie: Movie) -> Unit) {
        Log.d("imageUrl", movie.imageUrl)
        with(viewBinding) {
            listTags.text = movie.genres.map { it.name }.joinToString(", ") //TODO
            listReviews.text = movie.reviewCount.toString()
            listTitle.text = movie.title
            listDuraction.text = movie.releaseDate
            listImage.load(movie.imageUrl)
        }
        topImage.load(movie.imageUrl)


        Log.d("color_if", "${stars}}")
        stars.forEachIndexed { index, imageView ->
            if (index <= movie.rating) {
                imageView?.setImageResource(R.drawable.ic_star_icon)
                Log.d("color_if", " movie id ${movie.id} + index ${stars}")

            } else {
                imageView?.setImageResource(R.drawable.star_icon_gray)
                Log.d("color_else", "movie id ${movie.id} + index $index")
            }
        }
        itemView.setOnClickListener {
            onItemClicked(movie)
        }
    }
}


class MovieListCallBack : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}