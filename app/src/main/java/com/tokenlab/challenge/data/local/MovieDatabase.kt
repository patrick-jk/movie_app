package com.tokenlab.challenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tokenlab.challenge.data.local.dao.DetailedMovieDao
import com.tokenlab.challenge.data.local.dao.MovieDao
import com.tokenlab.challenge.data.local.entity.DetailedMovieEntity
import com.tokenlab.challenge.data.local.entity.MovieEntity
import com.tokenlab.challenge.data.local.util.GenresConverter
import com.tokenlab.challenge.data.local.util.MovieCollectionConverter

@Database(
    entities = [MovieEntity::class, DetailedMovieEntity::class], version = 1, exportSchema = false
)
@TypeConverters(GenresConverter::class, MovieCollectionConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val detailedMovieDao: DetailedMovieDao
}