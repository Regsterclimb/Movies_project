package com.example.moviesproject.hardcodedatalist

import com.example.moviesproject.R
import com.example.moviesproject.data.Actor

class ActorsDataSource {

    fun getActors() : List<Actor> {
        return listOf(
            Actor(R.drawable.robert, "Robert Downey Jr."),
            Actor(R.drawable.chris, "Chris Evans"),
            Actor(R.drawable.hzkto, "Mark Ruffalo"),
            Actor(R.drawable.hemsforth, "Chris Hemsworth"),
        )
    }
}