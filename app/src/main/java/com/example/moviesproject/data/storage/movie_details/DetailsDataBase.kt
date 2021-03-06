package com.example.moviesproject.data.storage.movie_details

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesproject.data.storage.Converters
import com.example.moviesproject.data.storage.entity.MovieDetailsEntity

@Database(entities = [MovieDetailsEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class DetailsDataBase : RoomDatabase() {
    abstract fun getMovieDetailsDao(): MovieDetailsDao

    companion object {
        fun create(appContext: Context): DetailsDataBase = Room.databaseBuilder(
            appContext, DetailsDataBase::class.java, MovieDetailsEntity.MovieDetailsDb.tableName
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}