package com.darleyleal.movieapp.ui.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.darleyleal.movieapp.model.Movie
import com.darleyleal.movieapp.navigation.AppRoutes
import com.darleyleal.movieapp.ui.components.update_movies_form.CategoryUpdateFieldForm
import com.darleyleal.movieapp.ui.components.update_movies_form.DescriptionUpdateFieldForm
import com.darleyleal.movieapp.ui.components.update_movies_form.ImageUpdateLinkFieldForm
import com.darleyleal.movieapp.ui.components.update_movies_form.NameUpdateFieldForm
import com.darleyleal.movieapp.ui.components.update_movies_form.YearUpdateFieldForm
import com.darleyleal.movieapp.ui.components.update_movies_form.YoutubeUpdateFieldForm
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UpdateItem(
    movieFormViewModel: MovieFormViewModel,
    navController: NavController,
    movie: Movie,
    modifier: Modifier = Modifier
) {
    MovieAppTheme {
        Scaffold {
            val context = LocalContext.current
            TopBarDefault(text = "Update Movie", navController = navController)
            Column {
                Spacer(modifier = modifier.padding(64.dp))
                NameUpdateFieldForm(
                    movie = movie,
                    movieFormViewModel = movieFormViewModel
                )

                Spacer(modifier.padding(8.dp))
                DescriptionUpdateFieldForm(
                    movie = movie,
                    movieFormViewModel = movieFormViewModel
                )

                Spacer(modifier = modifier.padding(8.dp))
                ImageUpdateLinkFieldForm(
                    movie = movie,
                    movieFormViewModel = movieFormViewModel
                )

                Spacer(modifier = modifier.padding(8.dp))
                YoutubeUpdateFieldForm(
                    movie = movie,
                    movieFormViewModel = movieFormViewModel
                )

                Spacer(modifier = modifier.padding(8.dp))
                YearUpdateFieldForm(
                    movie = movie,
                    movieFormViewModel = movieFormViewModel
                )

                Spacer(modifier = modifier.padding(8.dp))
                CategoryUpdateFieldForm(
                    movie = movie,
                    movieFormViewModel = movieFormViewModel
                )

                Spacer(modifier = modifier.padding(top = 16.dp))
                Button(
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    onClick = {
                        if (!movieFormViewModel.validateFieldsUpdate()) {
                            Toast.makeText(
                                context,
                                "The fields is requered!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            movieFormViewModel.update(movie.id)
                            navController.navigate(AppRoutes.HOME)
                        }
                    }
                ) {
                    Text(text = "Atualizar".uppercase(), fontSize = 18.sp)
                }
            }
        }
    }
}