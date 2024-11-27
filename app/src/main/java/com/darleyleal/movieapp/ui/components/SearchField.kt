@file:Suppress("UNUSED_EXPRESSION")

package com.darleyleal.movieapp.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.darleyleal.movieapp.R
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchField(
    movieFormViewModel: MovieFormViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    MovieAppTheme {
        var text by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }
        var listOfMovies = movieFormViewModel.movies

        Scaffold(
            topBar = {
                SearchBar(
                    query = text,
                    onQueryChange = { text = it },
                    onSearch = { Log.i("SearchField", "Performing search on query: $it") },
                    active = active,
                    onActiveChange = { false },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search_bar)
                        )
                    },
                    trailingIcon = {
                        if (text.isNotBlank()) {
                            Icon(
                                modifier = modifier.clickable { text = "" },
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(R.string.close_icon)
                            )
                        }
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                    shape = RoundedCornerShape(100.dp),
                    placeholder = { Text(text = stringResource(R.string.what_are_you_looking_for)) }
                ) {}
            }
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 1),
                modifier = modifier.padding(top = 122.dp)
            ) {
                when {
                    text.isBlank() -> {
                        items(listOfMovies.value) {
                            MovieCardItem(
                                movie = it, movieFormViewModel = movieFormViewModel,
                                navController = navController
                            )
                        }
                    }

                    else -> {
                        items(listOfMovies.value) {
                            val containsValue = remember(text) {
                                it.name?.contains(text, true) == true ||
                                        it.description?.contains(text, true) == true ||
                                        it.category?.contains(text, true) == true ||
                                        it.name?.contains(text, true) == true ||
                                        it.year?.contains(text, true) == true
                            }

                            if (containsValue) {
                                MovieCardItem(
                                    movie = it, movieFormViewModel = movieFormViewModel,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}