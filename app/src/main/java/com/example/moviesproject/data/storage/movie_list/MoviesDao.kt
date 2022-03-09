package com.example.moviesproject.data.storage.movie_list

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesproject.data.storage.entity.MovieEntity

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies_storage")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("DELETE FROM movies_storage")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(list: List<MovieEntity>)
}