package com.tokenlab.challenge.data.repo

import com.tokenlab.challenge.domain.model.DetailedMovie
import com.tokenlab.challenge.domain.model.Movie
import com.tokenlab.challenge.util.MovieState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<MovieState<List<Movie>>>

    fun getDetailedMovie(id: Int): Flow<MovieState<DetailedMovie>>
}