package com.tokenlab.challenge.domain.model

import com.google.gson.annotations.SerializedName
import com.tokenlab.challenge.data.local.entity.DetailedMovieEntity

data class DetailedMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val tagline: String,
    val genres: List<String>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("backdrop_url") val backdropUrl: String,
    @SerializedName("belongs_to_collection") val movieCollection: MovieCollection?,
) {
    fun toDetailedMovieEntity() = DetailedMovieEntity(
        id = id,
        title = title,
        overview = overview,
        tagline = tagline,
        genres = genres,
        originalLanguage = originalLanguage,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        backdropUrl = backdropUrl,
        movieCollection = movieCollection
    )
}

data class MovieCollection(
    @SerializedName("poster_url") val posterUrl: String,
    @SerializedName("backdrop_url") val backdropUrl: String,
)
