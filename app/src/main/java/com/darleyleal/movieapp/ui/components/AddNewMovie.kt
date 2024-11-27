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
import com.darleyleal.movieapp.ui.components.add_movies_form.CategoryFieldForm
import com.darleyleal.movieapp.ui.components.add_movies_form.DescriptionFieldForm
import com.darleyleal.movieapp.ui.components.add_movies_form.ImageLinkFieldForm
import com.darleyleal.movieapp.ui.components.add_movies_form.NameFieldForm
import com.darleyleal.movieapp.ui.components.add_movies_form.YearFieldForm
import com.darleyleal.movieapp.ui.components.add_movies_form.YoutubeFieldForm
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddNewMovie(
    movieFormViewModel: MovieFormViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {}
) {
    MovieAppTheme {
        Scaffold {
            val context = LocalContext.current
            TopBarDefault(text = "Add New Movie", navController = navController)
            Column {
                Spacer(modifier = modifier.padding(top = 124.dp))
                NameFieldForm(movieFormViewModel)

                Spacer(modifier = modifier.padding(top = 8.dp))
                DescriptionFieldForm(movieFormViewModel)

                Spacer(modifier = modifier.padding(top = 8.dp))
                ImageLinkFieldForm(movieFormViewModel)

                Spacer(modifier = modifier.padding(top = 8.dp))
                YoutubeFieldForm(movieFormViewModel)

                Spacer(modifier = modifier.padding(top = 8.dp))
                YearFieldForm(movieFormViewModel)

                Spacer(modifier = modifier.padding(top = 8.dp))
                CategoryFieldForm(movieFormViewModel)

                Spacer(modifier = modifier.padding(top = 16.dp))
                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        if (!movieFormViewModel.validateFieldsAddNewMovieForm()) {
                            Toast.makeText(
                                context,
                                "The fields is requered!", Toast.LENGTH_SHORT
                            ).show()
                        }
                        else {
                            movieFormViewModel.insert()
                            Toast.makeText(
                                context,
                                "New movie registered successfully!",
                                Toast.LENGTH_SHORT
                            ).show()
                            onNavigateToHome()
                        }
                    }) {
                    Text(text = "Save".uppercase(), fontSize = 18.sp)
                }
            }
        }
    }
}