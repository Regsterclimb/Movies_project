package com.example.moviesproject.data.data_base.movie_list

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesproject.data.data_base.Converters
import com.example.moviesproject.data.data_base.entity.MovieEntity


@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class DataBase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    init {
        Log.d("Database", "Init")
    }

    companion object {
        fun create(appContext: Context): DataBase = Room.databaseBuilder(
            appContext, DataBase::class.java, MovieEntity.MovieDb.tableName
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}