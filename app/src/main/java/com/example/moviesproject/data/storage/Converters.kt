package com.example.moviesproject.data.storage

import androidx.room.TypeConverter
import com.example.moviesproject.data.remote.respones.ActorResponse
import com.example.moviesproject.data.remote.respones.GenreResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    private val json = Json { ignoreUnknownKeys }

    @TypeConverter
    fun genresToString(list: List<GenreResponse>): String = json.encodeToString(list)

    @TypeConverter
    fun genresToList(string: String): List<GenreResponse> = json.decodeFromString(string)

    @TypeConverter
    fun actorsToString(list: List<ActorResponse>): String = json.encodeToString(list)

    @TypeConverter
    fun actorsToList(string: String): List<ActorResponse> = json.decodeFromString(string)
}