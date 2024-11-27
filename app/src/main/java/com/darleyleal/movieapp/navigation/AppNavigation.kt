package com.darleyleal.movieapp.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.darleyleal.movieapp.MyApp
import com.darleyleal.movieapp.ui.screens.AddNewMovieScreen
import com.darleyleal.movieapp.ui.screens.HomeScreen
import com.darleyleal.movieapp.ui.screens.OpenCardInFullScreen
import com.darleyleal.movieapp.ui.screens.SearchFieldScreen
import com.darleyleal.movieapp.ui.screens.UpdateMovieScreen
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    movieFormViewModel: MovieFormViewModel
    ) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {
        composable(route = AppRoutes.HOME) {
            MyApp {
                HomeScreen(
                    navController = navController,
                    movieFormViewModel = movieFormViewModel
                )
            }
        }
        composable(route = AppRoutes.SEARCH_FIELD) {
            SearchFieldScreen(
                navController = navController,
                movieFormViewModel = movieFormViewModel
            )
        }
        composable(route = AppRoutes.ADD_NEW_MOVIE) {
            AddNewMovieScreen(
                navController = navController,
                movieFormViewModel = movieFormViewModel,
                onNavigateToHome = {
                    navController.navigate(AppRoutes.HOME)
                }
            )
        }
        val movieIdType: NavType<String?> = NavType.StringType

        composable(
            route = AppRoutes.OPEN_CARD_IN_FULL_SCREEN + "/{movieId}", arguments = listOf(
                navArgument("movieId") {
                    type = movieIdType
                }
            )
        ) { backStackEntry ->

            val movieId = backStackEntry.arguments?.getString("movieId")
            val listOfMovies = movieFormViewModel.movies.value

            val movie = listOfMovies.filter {
                it.id.toString() == movieId
            }

            OpenCardInFullScreen(
                movie.first(),
                navController = navController
            )
        }

        composable(
            route = AppRoutes.UPDATE_MOVIE + "/{movieId}", arguments = listOf(
                navArgument("movieId") {
                    type = movieIdType
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            val listOfMovies = movieFormViewModel.movies.value

            val movie = listOfMovies.filter {
                it.id.toString() == movieId
            }

            UpdateMovieScreen(
                movieFormViewModel = movieFormViewModel,
                navController = navController,
                movie = movie.first()
            )
        }
    }
}