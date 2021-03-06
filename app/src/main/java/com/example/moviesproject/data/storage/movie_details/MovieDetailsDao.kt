package com.example.moviesproject.data.storage.movie_details

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesproject.data.storage.entity.MovieDetailsEntity

@Dao
interface MovieDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetails(detailsEntity: MovieDetailsEntity)

    @Query("SELECT * FROM movie_details_storage WHERE id = :id ")
    fun getMovieDetails(id: Int): MovieDetailsEntity?
}
