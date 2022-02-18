package com.example.moviesproject.data.data_base

import androidx.room.TypeConverter
import com.example.moviesproject.data.remote.respones.GenreResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    private val json = Json { ignoreUnknownKeys }

    @TypeConverter
    fun genresToString(list: List<GenreResponse>): String {
        return json.encodeToString(list)
    }

    @TypeConverter
    fun genresToList(string: String): List<GenreResponse> {
        return json.decodeFromString(string)
    }
}