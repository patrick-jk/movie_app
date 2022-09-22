package com.tokenlab.challenge.domain.usecase

import com.tokenlab.challenge.data.repo.MovieRepository

class GetDetailedMovieUseCase(private val repository: MovieRepository) {
    operator fun invoke(id: Int) = repository.getDetailedMovie(id)
}