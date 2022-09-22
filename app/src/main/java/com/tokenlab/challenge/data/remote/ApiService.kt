package com.tokenlab.challenge.data.remote

import com.tokenlab.challenge.domain.model.DetailedMovie
import com.tokenlab.challenge.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movies-v2")
    suspend fun getMovies(): List<Movie>

    @GET("movies-v2/{id}")
    suspend fun getDetailedMovie(@Path("id") id: Int): DetailedMovie

    companion object {
        const val BASE_URL = "https://desafio-mobile.nyc3.digitaloceanspaces.com/"
    }
}