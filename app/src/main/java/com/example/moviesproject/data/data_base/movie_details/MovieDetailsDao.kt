package com.example.moviesproject.data.data_base.movie_details

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviesproject.data.data_base.entity.MovieDetailsEntity

@Dao
interface MovieDetailsDao {
    @Insert
    fun insertMovieDetails(detailsEntity: MovieDetailsEntity)

    @Query("SELECT * FROM movie_details_storage WHERE id = :id ")
    fun getMovieDetails(id: Int): MovieDetailsEntity?
}
