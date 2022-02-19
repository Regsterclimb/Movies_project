package com.example.moviesproject.data.data_base.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesproject.data.remote.respones.GenreResponse
import com.example.moviesproject.domain.model.Movie

@Entity(tableName = MovieEntity.MovieDb.tableName)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MovieDb.Column.id)
    var id: Int,
    @ColumnInfo(name = MovieDb.Column.title)
    var title: String,
    @ColumnInfo(name = MovieDb.Column.storyLine)
    var storyLine: String,
    @ColumnInfo(name = MovieDb.Column.imageUrl)
    var imageUrl: String,
    @ColumnInfo(name = MovieDb.Column.detailImageUrl)
    var detailImageUrl: String,
    @ColumnInfo(name = MovieDb.Column.rating)
    var rating: Int,
    @ColumnInfo(name = MovieDb.Column.reviewCount)
    var reviewCount: Int,
    @ColumnInfo(name = MovieDb.Column.pgAge)
    var pgAge: Int,
    @ColumnInfo(name = MovieDb.Column.releaseDate)
    var releaseDate: String,
    @ColumnInfo(name = MovieDb.Column.genreResponses)
    var genreResponses: List<GenreResponse>,
    @ColumnInfo(name = MovieDb.Column.isLiked)
    var isLiked: Boolean
) {
    object MovieDb {
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
