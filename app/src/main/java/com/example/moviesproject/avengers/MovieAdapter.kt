package com.example.moviesproject.avengers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesproject.R
import com.example.moviesproject.data.Movie

class MovieAdapter(private val onItemListner: clickOnMovie) :
    RecyclerView.Adapter<MovieDataViewHolder>() {

    private var movieList = listOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDataViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        return MovieDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieDataViewHolder, position: Int) {

        holder.onBind(movieList[position])

        holder.itemView.apply {
            setOnClickListener {
                onItemListner.clickOnTopFragment(movieList[position])
            }
        }
    }

override fun getItemCount(): Int {
    return movieList.size
}

fun bindMovies(Newmovie: List<Movie>) {
    movieList = Newmovie
}


interface clickOnMovie {
    fun clickOnTopFragment(movie: Movie)
}

}