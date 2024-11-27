package com.darleyleal.movieapp.database

import android.content.Context
import com.darleyleal.movieapp.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieAppRepository(context: Context) {
    private val dao: MovieAppDAO = MovieAppDatabase.getDatabase(context).movieAppDAO()

    private val _allMovies = MutableStateFlow<List<Movie>>(emptyList())
    val allMovies: StateFlow<List<Movie>> = _allMovies.asStateFlow()

    fun getAllMovies(): List<Movie> = dao.getAllMovies()

    fun getMovie(id: Int): Movie {
        return dao.getMovie(id)
    }

    fun insertMovie(
        name: String, description: String,
        image: String, year: String,
        category: String, youtubeLink: String
    ): Boolean {
        val movie = Movie(
            name = name,
            description = description,
            image = image,
            year = year,
            category = category,
            youtubeLinkVideo = youtubeLink
        )
        return dao.insertMovie(movie) > 0
    }

    fun deleteMovie(id: Long) {
        return dao.deleteMovie(id)
    }

    fun updateMovie(
        movieId: Long, name: String, description: String,
        imageLink: String, year: String, category: String, youtubeLink: String
    ) {
        dao.updateAllFieldsMovie(
            id = movieId,
            name = name,
            description = description,
            imageLink = imageLink,
            year = year,
            category = category,
            youtubeLink = youtubeLink
        )
    }

    fun getAllMoviesByCategory(): List<Movie> {
        return dao.getAllMovies()
    }
}