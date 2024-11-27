package com.darleyleal.movieapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.darleyleal.movieapp.model.Movie

@Dao
interface MovieAppDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Long

    @Update
    fun updateMovie(movie: Movie): Int

    @Query("DELETE FROM movies WHERE id = :id")
    fun deleteMovie(id: Long)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovie(id: Int): Movie

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies GROUP BY category")
    fun getAllMoviesByCategory(): List<Movie>

    @Query(
        """UPDATE movies
             SET name = :name,
                 description = :description,
                 image = :imageLink,
                 year = :year, 
                 category =:category,
                 youtubeLinkVideo =:youtubeLink 
            WHERE id = :id
        """
    )
    fun updateAllFieldsMovie(
        id: Long, name: String, description: String,
        imageLink: String, year: String,
        category: String, youtubeLink: String
    )
}