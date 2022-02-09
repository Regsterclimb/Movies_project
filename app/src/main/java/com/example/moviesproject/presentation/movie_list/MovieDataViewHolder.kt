package com.example.moviesproject.presentation.movie_list

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.moviesproject.R
import com.example.moviesproject.databinding.ViewHolderMovieBinding
import com.example.moviesproject.domain.model.Movie

class MovieDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(ViewHolderMovieBinding::bind)

    fun onBind(movie: Movie, onItemClicked: (movie: Movie) -> Unit) {
        with(viewBinding) {
            listTags.text = movie.genreResponses.joinToString(", ") { it.name } //TODO
            listReviews.text = movie.reviewCount.toString()
            listTitle.text = movie.title
            listDuraction.text = movie.releaseDate
            listImage.load(movie.imageUrl)
            listOf(
                viewBinding.listStar1,
                viewBinding.listStar2,
                viewBinding.listStar3,
                viewBinding.listStar4,
                viewBinding.listStar5,
            ).forEachIndexed { index, imageView ->
                val colorId = if (movie.rating > index) R.color.tag_color_red else R.color.gray_gray
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }
        }
        itemView.setOnClickListener {
            onItemClicked(movie)
        }
    }
}