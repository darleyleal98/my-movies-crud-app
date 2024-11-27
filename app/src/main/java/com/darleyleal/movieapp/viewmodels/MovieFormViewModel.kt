package com.darleyleal.movieapp.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.darleyleal.movieapp.database.MovieAppRepository
import com.darleyleal.movieapp.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieAppRepository(application.applicationContext)

    var name by mutableStateOf("")
    var description by mutableStateOf("")
    var imageLink by mutableStateOf("")
    var year by mutableStateOf("")
    var category by mutableStateOf("")
    var youtubeLink by mutableStateOf("")

    var nameUpdate by mutableStateOf("")
    var descriptionUpdate by mutableStateOf("")
    var imageLinkUpdate by mutableStateOf("")
    var yearUpdate by mutableStateOf("")
    var categoryUpdate by mutableStateOf("")
    var youtubeLinkUpdate by mutableStateOf("")

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        viewModelScope.launch {
            _movies.value = listAllMovies()
        }
    }

    fun insert() {
        repository.insertMovie(
            name, description,
            imageLink, year, category,
            youtubeLink
        )
        viewModelScope.launch {
            _movies.value = listAllMovies()
        }
        cleanFieldsForm()
    }

    fun delete(id: Long) {
        repository.deleteMovie(id)
        viewModelScope.launch {
            _movies.value = listAllMovies()
        }
    }

    fun update(id: Long) {
        repository.updateMovie(
            id, nameUpdate, descriptionUpdate, imageLinkUpdate,
            yearUpdate, categoryUpdate,
            youtubeLinkUpdate
        )
        viewModelScope.launch {
            _movies.value = listAllMovies()
        }
        cleanFieldsForm()
    }

    private fun listAllMovies(): List<Movie> {
        return repository.getAllMovies()
    }

    fun getMovie(id: Int): Movie {
        return repository.getMovie(id)
    }

    fun getAllMoviesByCategory(): List<Movie> {
        return repository.getAllMoviesByCategory()
    }

    fun validateFieldsAddNewMovieForm(): Boolean {
        when {
            name.trim().isEmpty()
                    || description.trim().isEmpty()
                    || imageLink.trim().isEmpty()
                    || year.trim().isEmpty()
                    || category.trim().isEmpty()
                    || youtubeLink.trim().isEmpty() -> {
                return false
            }
        }
        return true
    }

    fun validateFieldsUpdate(): Boolean {
        when {
            nameUpdate.trim().isEmpty()
                    || descriptionUpdate.trim().isEmpty()
                    || imageLinkUpdate.trim().isEmpty()
                    || yearUpdate.trim().isEmpty()
                    || categoryUpdate.trim().isEmpty()
                    || youtubeLinkUpdate.trim().isEmpty() -> {
                return false
            }
        }
        return true
    }

    private fun cleanFieldsForm() {
        name = ""
        description = ""
        imageLink = ""
        year = ""
        category = ""
        youtubeLink = ""
    }
}