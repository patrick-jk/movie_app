package com.tokenlab.challenge.data.repo

import com.tokenlab.challenge.data.local.dao.DetailedMovieDao
import com.tokenlab.challenge.data.local.dao.MovieDao
import com.tokenlab.challenge.data.remote.ApiService
import com.tokenlab.challenge.domain.model.DetailedMovie
import com.tokenlab.challenge.domain.model.Movie
import com.tokenlab.challenge.util.MovieState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    private val detailedMovieDao: DetailedMovieDao
) : MovieRepository {
    override fun getMovies(): Flow<MovieState<List<Movie>>> = flow {
        emit(MovieState.Loading())

        try {
            val remoteMovies = apiService.getMovies()
            movieDao.deleteMovies()
            movieDao.insertMovies(remoteMovies.map { it.toMovieEntity() })
        } catch (e: HttpException) {
            emit(MovieState.Error(e))
        } catch (e: IOException) {
            emit(MovieState.Error(e))
        }

        val updatedMovies = movieDao.getMovies().map { it.toMovie() }
        emit(MovieState.Success(updatedMovies))
    }

    override fun getDetailedMovie(id: Int): Flow<MovieState<DetailedMovie>> = flow {
        emit(MovieState.Loading())

        try {
            val remoteDetailedMovie = apiService.getDetailedMovie(id)
            detailedMovieDao.deleteDetailedMovie(id)
            detailedMovieDao.insert(remoteDetailedMovie.toDetailedMovieEntity())
        } catch (e: HttpException) {
            emit(MovieState.Error(e))
        } catch (e: IOException) {
            emit(MovieState.Error(e))
        }

        val updatedDetailedMovie = detailedMovieDao.getDetailedMovie(id)
        if (updatedDetailedMovie != null) {
            emit(MovieState.Success(updatedDetailedMovie.toDetailedMovie()))
        } else {
            emit(MovieState.Error(NullPointerException()))
        }
    }
}