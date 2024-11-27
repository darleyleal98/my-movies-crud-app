package com.darleyleal.movieapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.darleyleal.movieapp.ui.components.AddNewMovie
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddNewMovieScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    movieFormViewModel: MovieFormViewModel
) {
    MovieAppTheme {
        Scaffold {
            AddNewMovie(
                navController = navController,
                movieFormViewModel = movieFormViewModel, modifier = modifier,
                onNavigateToHome = onNavigateToHome
            )
        }
    }
}