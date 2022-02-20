package com.example.moviesproject.data.data_base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesproject.data.data_base.entity.MovieDetailsEntity
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Database(entities = [MovieDetailsEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class DetailsDataBase : RoomDatabase() {
    abstract fun getMovieDetailsDao() : MovieDetailsDao

    companion object {
        fun create(appContext: Context): DetailsDataBase = Room.databaseBuilder(
            appContext, DetailsDataBase::class.java, MovieDetailsEntity.MovieDetailsDb.tableName
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}