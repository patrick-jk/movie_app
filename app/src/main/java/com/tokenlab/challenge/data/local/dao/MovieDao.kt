package com.tokenlab.challenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tokenlab.challenge.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie_entity ORDER BY title ASC")
    suspend fun getMovies(): List<MovieEntity>

    @Query("DELETE FROM movie_entity")
    suspend fun deleteMovies()
}