package com.example.moviesproject.data.storage.movie_list

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesproject.data.storage.Converters
import com.example.moviesproject.data.storage.entity.MovieEntity


@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class DataBase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        fun create(appContext: Context): DataBase = Room.databaseBuilder(
            appContext, DataBase::class.java, MovieEntity.MovieDb.tableName
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}