package com.example.moviesproject.data.data_base.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesproject.data.remote.respones.GenreResponse

@Entity(tableName = MovieDb.MovieDbArticle.tableName)
data class MovieDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = MovieDbArticle.Column.title)
    val title: String,
    @ColumnInfo(name = MovieDbArticle.Column.storyLine)
    val storyLine: String,
    @ColumnInfo(name = MovieDbArticle.Column.imageUrl)
    val imageUrl: String,
    @ColumnInfo(name = MovieDbArticle.Column.detailImageUrl)
    val detailImageUrl: String,
    @ColumnInfo(name = MovieDbArticle.Column.rating)
    val rating: Int,
    @ColumnInfo(name = MovieDbArticle.Column.reviewCount)
    val reviewCount: Int,
    @ColumnInfo(name = MovieDbArticle.Column.pgAge)
    val pgAge: Int,
    @ColumnInfo(name = MovieDbArticle.Column.releaseDate)
    val releaseDate: String,
    @ColumnInfo(name = MovieDbArticle.Column.genreResponses)
    val genreResponses: List<GenreResponse>,
    @ColumnInfo(name = MovieDbArticle.Column.isLiked)
    val isLiked: Boolean
) {
    object MovieDbArticle {
        const val tableName = "movies_storage"

        object Column {
            const val id = "id"
            const val title = "title"
            const val storyLine = "storyLine"
            const val imageUrl = "imageUrl"
            const val detailImageUrl = "detailImageUrl"
            const val rating = "rating"
            const val reviewCount = "reviewCount"
            const val pgAge = "pgAge"
            const val releaseDate = "releaseDate"
            const val genreResponses = "genreResponses"
            const val isLiked = "isLiked"
        }
    }
}
