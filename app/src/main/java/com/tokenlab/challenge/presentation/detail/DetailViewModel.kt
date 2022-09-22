package com.tokenlab.challenge.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokenlab.challenge.domain.model.DetailedMovie
import com.tokenlab.challenge.domain.usecase.GetDetailedMovieUseCase
import com.tokenlab.challenge.util.MovieState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailViewModel(private val getDetailedMovieUseCase: GetDetailedMovieUseCase) : ViewModel() {
    private val _selectedMovie = MutableLiveData<MovieState<DetailedMovie>>()
    val selectedMovie: LiveData<MovieState<DetailedMovie>> = _selectedMovie

    fun getMovie(id: Int) = viewModelScope.launch {
        getDetailedMovieUseCase(id).onEach {
            _selectedMovie.value = it
        }.launchIn(this)
    }
}