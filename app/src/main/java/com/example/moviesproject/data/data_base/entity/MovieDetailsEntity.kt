package com.example.moviesproject.data.data_base.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesproject.data.remote.respones.ActorResponse
import com.example.moviesproject.data.remote.respones.GenreResponse

@Entity(tableName = MovieDetailsEntity.MovieDetailsDb.tableName)
data class MovieDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MovieDetailsDb.Column.id)
    val id: Int,
    @ColumnInfo(name = MovieDetailsDb.Column.title)
    val title: String,
    @ColumnInfo(name = MovieDetailsDb.Column.detailImageUrl)
    val detailImageUrl: String,
    @ColumnInfo(name = MovieDetailsDb.Column.revenue)
    val revenue: Int,
    @ColumnInfo(name = MovieDetailsDb.Column.genreResponses)
    val genreResponses: List<GenreResponse>,
    @ColumnInfo(name = MovieDetailsDb.Column.reviewCount)
    val reviewCount: Int,
    @ColumnInfo(name = MovieDetailsDb.Column.budget)
    val budget: Int,
    @ColumnInfo(name = MovieDetailsDb.Column.storyLine)
    val storyLine: String,
    @ColumnInfo(name = MovieDetailsDb.Column.runtime)
    val runtime: Int,
    @ColumnInfo(name = MovieDetailsDb.Column.imageUrl)
    val imageUrl: String,
    @ColumnInfo(name = MovieDetailsDb.Column.releaseDate)
    val releaseDate: String,
    @ColumnInfo(name = MovieDetailsDb.Column.rating)
    val rating: Int,
    @ColumnInfo(name = MovieDetailsDb.Column.tagLine)
    val tagLine: String,
    @ColumnInfo(name = MovieDetailsDb.Column.actorResponseList)
    val actorResponseList: List<ActorResponse>,
    @ColumnInfo(name = MovieDetailsDb.Column.pgAge)
    val pgAge: String
) {
    object MovieDetailsDb {
        const val tableName = "movie_details_storage"

        object Column {
            const val id = "id"
            const val title = "title"
            const val detailImageUrl = "detailImageUrl"
            const val revenue = "revenue"
            const val genreResponses = "genreResponses"
            const val reviewCount = "reviewCount"
            const val budget = "budget"
            const val storyLine = "storyLine"
            const val runtime = "runtime"
            const val imageUrl = "imageUrl"
            const val releaseDate = "releaseDate"
            const val rating = "rating"
            const val tagLine = "tagLine"
            const val actorResponseList = "actorResponseList"
            const val pgAge = "pgAge"
        }
    }
}
