package com.tokenlab.challenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tokenlab.challenge.data.local.entity.DetailedMovieEntity

@Dao
interface DetailedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(detailedMovieEntity: DetailedMovieEntity)

    @Query("SELECT * FROM detailed_movie_entity WHERE id = :id")
    suspend fun getDetailedMovie(id: Int): DetailedMovieEntity?

    @Query("DELETE FROM detailed_movie_entity WHERE id = :id")
    suspend fun deleteDetailedMovie(id: Int)
}