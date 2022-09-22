package com.tokenlab.challenge.data.local.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.tokenlab.challenge.domain.model.MovieCollection

class MovieCollectionConverter {
    @TypeConverter
    fun fromMovieCollection(movieCollection: MovieCollection?): String? =
        Gson().toJson(movieCollection)

    @TypeConverter
    fun toMovieCollection(collectionInfo: String?): MovieCollection? {
        return Gson().fromJson(collectionInfo, MovieCollection::class.java)
    }
}