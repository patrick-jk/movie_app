package com.tokenlab.challenge.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tokenlab.challenge.domain.model.Movie
import com.tokenlab.challenge.domain.usecase.GetMoviesUseCase
import com.tokenlab.challenge.util.MovieState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(private val getMoviesUseCase: GetMoviesUseCase) : ViewModel() {
    private val _movies = MutableLiveData<MovieState<List<Movie>>>()
    val movies: LiveData<MovieState<List<Movie>>> = _movies

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getMovies()
        viewModelScope.launch {
            delay(1500)
            _isLoading.value = false
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            getMoviesUseCase().onEach {
                _movies.value = it
            }.launchIn(this)
        }
    }
}