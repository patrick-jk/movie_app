package com.tokenlab.challenge.domain.usecase

import com.tokenlab.challenge.data.repo.MovieRepository

class GetMoviesUseCase(private val repository: MovieRepository) {
    operator fun invoke() = repository.getMovies()
}