package com.darleyleal.movieapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@Composable
fun MovieSection(
    movieFormViewModel: MovieFormViewModel,
    navController: NavController
) {
    MovieAppTheme {
        val movies by movieFormViewModel.movies.collectAsState()
        Surface {
            LazyColumn {
                items(movies) {
                    MovieCardItem(
                        navController = navController,
                        movie = it,
                        movieFormViewModel = movieFormViewModel
                    )
                }
            }
        }
    }
}