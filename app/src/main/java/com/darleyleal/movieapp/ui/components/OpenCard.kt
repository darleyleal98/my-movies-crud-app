package com.darleyleal.movieapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.darleyleal.movieapp.model.Movie
import com.darleyleal.movieapp.navigation.AppRoutes
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OpenCard(
    movie: Movie,
    navController: NavController,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    MovieAppTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text(text = movie.name.toString())
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Arrow back Icon",
                                modifier = modifier.padding(start = 8.dp)
                            )
                        }
                    }
                )
            }
        ) {
            Column(modifier = modifier.padding(top = 116.dp)) {
                YouTubePlayer(youtubeLink = movie.youtubeLinkVideo.toString())
                movie.category?.let {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(800),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier
                            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    )
                }
                movie.year?.let {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(800),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier
                            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    )
                }
                Text(
                    text = "Description",
                    fontWeight = FontWeight(800),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier.padding(start = 8.dp, bottom = 16.dp)
                )
                movie.description?.let {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        maxLines = 6,
                        fontWeight = FontWeight(500),
                        modifier = modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    )
                }
            }
        }
    }
}