package com.example.moviesproject.presentation.movie_list.support

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.example.moviesproject.R

interface StarsColor {

    fun setColor(list: List<ImageView>, movie: Int)

    class Base : StarsColor {

        override fun setColor(list: List<ImageView>, voteAverage: Int) {
            return list.forEachIndexed { index, imageView ->
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(
                            imageView.context,
                            if (voteAverage > index) R.color.tag_color_red else R.color.gray_gray
                        )
                    )
                )
            }
        }
    }
}