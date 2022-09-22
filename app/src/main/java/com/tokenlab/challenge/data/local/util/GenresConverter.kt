package com.tokenlab.challenge.data.local.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenresConverter {
    @TypeConverter
    fun fromGenresList(genres: List<String>): String = Gson().toJson(genres)

    @TypeConverter
    fun toGenresList(genres: String): List<String> {
        val genresList = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(genres, genresList)
    }
}