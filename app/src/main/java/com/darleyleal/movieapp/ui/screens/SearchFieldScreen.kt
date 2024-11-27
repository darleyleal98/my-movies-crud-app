package com.darleyleal.movieapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.darleyleal.movieapp.ui.components.SearchField
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@Composable
fun SearchFieldScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    movieFormViewModel: MovieFormViewModel
) {
    MovieAppTheme {
        SearchField(movieFormViewModel, navController, modifier)
    }
}