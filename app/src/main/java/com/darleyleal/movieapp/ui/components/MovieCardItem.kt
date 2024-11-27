package com.darleyleal.movieapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.darleyleal.movieapp.R
import com.darleyleal.movieapp.model.Movie
import com.darleyleal.movieapp.navigation.AppRoutes
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCardItem(
    movie: Movie,
    movieFormViewModel: MovieFormViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    MovieAppTheme {
        var expanded by remember { mutableStateOf(false) }
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            onClick = {
                expanded = !expanded
            },
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            if (!expanded) {
                Box(contentAlignment = Alignment.BottomStart) {
                    AsyncImage(
                        model = movie.image,
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.white_background),
                        error = painterResource(id = R.drawable.error_loading_image),
                        fallback = painterResource(id = R.drawable.white_background)
                    )
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    )
                                )
                            )
                    ) {
                        Column {
                            movie.name?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight(800),
                                    maxLines = 1,
                                    color = Color.White,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            bottom = 8.dp
                                        )
                                )
                            }
                            movie.year?.let {
                                Text(
                                    text = it,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(800),
                                    maxLines = 1,
                                    color = Color.White,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            bottom = 4.dp
                                        )
                                )
                            }
                        }
                    }
                }
            } else {
                AnimatedVisibility(
                    visible = expanded
                ) {
                    Column {
                        Spacer(modifier = modifier.padding(bottom = 8.dp))
                        YouTubePlayer(youtubeLink = movie.youtubeLinkVideo.toString())
                        movie.name?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight(800),
                                maxLines = 1,
                                fontSize = 24.sp,
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 8.dp,
                                        end = 8.dp,
                                        bottom = 8.dp
                                    )
                            )
                        }
                        movie.year?.let {
                            Text(
                                text = it,
                                fontSize = 22.sp,
                                fontWeight = FontWeight(800),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 8.dp,
                                        end = 8.dp,
                                        bottom = 4.dp
                                    )
                            )
                        }
                        Row(
                            modifier = modifier
                                .padding(
                                    start = 8.dp,
                                    end = 8.dp, bottom = 16.dp
                                )
                                .fillMaxWidth(), horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(onClick = {
                                navController.navigate(
                                    AppRoutes.OPEN_CARD_IN_FULL_SCREEN + "/${movie.id}"
                                )
                            }) {
                                Icon(
                                    imageVector = Icons.Default.OpenInFull,
                                    contentDescription = "Open in Full Button"
                                )
                            }
                            IconButton(onClick = {
                                navController.navigate(
                                    AppRoutes.UPDATE_MOVIE + "/${movie.id}"
                                )
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Icon"
                                )
                            }
                            IconButton(onClick = {
                                movieFormViewModel.delete(movie.id)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Icon"
                                )
                            }
                        }
                        Column(modifier = Modifier.padding(top = 64.dp, bottom = 16.dp)) {
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
                                    modifier = modifier.padding(
                                        start = 8.dp,
                                        end = 8.dp,
                                        bottom = 8.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}