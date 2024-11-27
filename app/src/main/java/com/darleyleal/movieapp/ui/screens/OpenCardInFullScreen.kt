package com.darleyleal.movieapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.darleyleal.movieapp.model.Movie
import com.darleyleal.movieapp.ui.components.OpenCard
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@Composable
fun OpenCardInFullScreen(
    movie: Movie,
    navController: NavController
) {
    MovieAppTheme {
        OpenCard(
            movie = movie,
            navController = navController
        )
    }
}