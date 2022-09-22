package com.tokenlab.challenge.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tokenlab.challenge.domain.model.Movie

@Entity(tableName = "movie_entity")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val voteAverage: Double,
    val posterUrl: String,
    val genres: List<String>,
    val releaseDate: String
) {
    fun toMovie() = Movie(
        id = id, title = title, voteAverage = voteAverage, posterUrl = posterUrl, genres = genres,
        releaseDate = releaseDate
    )
}