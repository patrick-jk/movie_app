package com.tokenlab.challenge.util

sealed class MovieState<T> {
    class Loading<T> : MovieState<T>()
    data class Success<T>(val data: T) : MovieState<T>()
    data class Error<T>(val error: Throwable) : MovieState<T>()
}

