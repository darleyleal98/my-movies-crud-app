package com.darleyleal.movieapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.darleyleal.movieapp.model.Movie
import com.darleyleal.movieapp.ui.components.UpdateItem
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@Composable
fun UpdateMovieScreen(
    movieFormViewModel: MovieFormViewModel,
    navController: NavController,
    movie: Movie,
    modifier: Modifier = Modifier
) {
    MovieAppTheme {
        UpdateItem(
            movieFormViewModel = movieFormViewModel,
            navController = navController,
            movie = movie
        )
    }
}