package com.example.moviesproject.data.data_base.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesproject.data.remote.respones.GenreResponse
import com.example.moviesproject.domain.model.Movie

@Entity(tableName = MovieEntity.MovieDbArticle.tableName)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MovieDbArticle.Column.id)
    var id: Int,
    @ColumnInfo(name = MovieDbArticle.Column.title)
    var title: String,
    @ColumnInfo(name = MovieDbArticle.Column.storyLine)
    var storyLine: String,
    @ColumnInfo(name = MovieDbArticle.Column.imageUrl)
    var imageUrl: String,
    @ColumnInfo(name = MovieDbArticle.Column.detailImageUrl)
    var detailImageUrl: String,
    @ColumnInfo(name = MovieDbArticle.Column.rating)
    var rating: Int,
    @ColumnInfo(name = MovieDbArticle.Column.reviewCount)
    var reviewCount: Int,
    @ColumnInfo(name = MovieDbArticle.Column.pgAge)
    var pgAge: Int,
    @ColumnInfo(name = MovieDbArticle.Column.releaseDate)
    var releaseDate: String,
    @ColumnInfo(name = MovieDbArticle.Column.genreResponses)
    var genreResponses: List<GenreResponse>,
    @ColumnInfo(name = MovieDbArticle.Column.isLiked)
    var isLiked: Boolean
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

fun MovieEntity.toMovie(): Movie = Movie(
    id,
    title,
    storyLine,
    imageUrl,
    detailImageUrl,
    rating,
    reviewCount,
    pgAge,
    releaseDate,
    genreResponses,
    isLiked
)
