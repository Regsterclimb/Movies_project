package com.example.moviesproject.presentation.movie_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.moviesproject.databinding.ViewHolderMovieBinding
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.presentation.support.StarsColor

class MovieDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(ViewHolderMovieBinding::bind)

    fun onBind(movie: Movie, onItemClicked: (movie: Movie) -> Unit, starsColor: StarsColor) {
        with(viewBinding) {
            listTags.text = movie.genreResponses.joinToString(", ") { it.name } //TODO
            listReviews.text = movie.reviewCount.toString()
            listTitle.text = movie.title
            listDuraction.text = movie.releaseDate
            listImage.load(movie.imageUrl)
            starsColor.setColor(
                listOf(
                    listStar1,
                    listStar2,
                    listStar3,
                    listStar4,
                    listStar5,
                ),
                movie.rating
            )
        }
        itemView.setOnClickListener {
            onItemClicked(movie)
        }
    }
}