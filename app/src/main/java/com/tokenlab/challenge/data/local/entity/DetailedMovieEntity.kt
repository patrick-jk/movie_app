package com.tokenlab.challenge.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tokenlab.challenge.domain.model.MovieCollection
import com.tokenlab.challenge.domain.model.DetailedMovie

@Entity(tableName = "detailed_movie_entity")
data class DetailedMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val tagline: String,
    val genres: List<String>,
    val originalLanguage: String,
    val releaseDate: String,
    val voteAverage: Double,
    val backdropUrl: String,
    val movieCollection: MovieCollection?,
) {
    fun toDetailedMovie() = DetailedMovie(
        id = id, title = title, overview = overview, tagline = tagline, genres = genres,
        originalLanguage = originalLanguage, releaseDate = releaseDate, voteAverage = voteAverage,
        backdropUrl = backdropUrl, movieCollection = movieCollection
    )
}