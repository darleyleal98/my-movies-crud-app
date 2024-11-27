package com.darleyleal.movieapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.OndemandVideo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.darleyleal.movieapp.navigation.AppRoutes
import com.darleyleal.movieapp.ui.components.MovieSection
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    movieFormViewModel: MovieFormViewModel
) {
    MovieAppTheme {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(AppRoutes.SEARCH_FIELD)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Menu Icon",
                                modifier = modifier.padding(start = 8.dp)
                            )
                        }
                    },
                    title = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.OndemandVideo,
                                contentDescription = "Menu Icon",
                                modifier = modifier.padding(start = 8.dp)
                            )
                            Text(text = "My Movies", modifier = modifier.padding(start = 8.dp))
                        }
                    }
                )
            }, floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(AppRoutes.ADD_NEW_MOVIE)
                    }
                ) {
                    Icon(Icons.Filled.Add, "Add Icon")
                }
            }
        ) {
            Column {
                Spacer(modifier = modifier.padding(top = 125.dp))
                MovieSection(
                    navController = navController,
                    movieFormViewModel = movieFormViewModel
                )
            }
        }
    }
}