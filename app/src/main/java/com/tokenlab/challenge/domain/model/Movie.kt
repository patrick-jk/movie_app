package com.tokenlab.challenge.domain.model

import com.google.gson.annotations.SerializedName
import com.tokenlab.challenge.data.local.entity.MovieEntity

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("poster_url") val posterUrl: String,
    val genres : List<String>,
    @SerializedName("release_date") val releaseDate: String
) {
    fun toMovieEntity() = MovieEntity(
        id = id,
        title = title,
        voteAverage = voteAverage,
        posterUrl = posterUrl,
        genres = genres,
        releaseDate = releaseDate
    )
}
