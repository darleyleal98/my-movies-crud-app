package com.darleyleal.movieapp.ui.components.update_movies_form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darleyleal.movieapp.R
import com.darleyleal.movieapp.model.Movie
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@Composable
fun DescriptionUpdateFieldForm(
    movieFormViewModel: MovieFormViewModel,
    movie: Movie,
    modifier: Modifier = Modifier
) {
    MovieAppTheme {
        var descriptionFieldIsValid by remember { mutableStateOf(true) }
        var description by remember { mutableStateOf(movie.description.toString())}
        movieFormViewModel.descriptionUpdate = description

        TextField(
            value = description,
            onValueChange = {
                description = it
                descriptionFieldIsValid = it.trim().isNotEmpty()
            },
            isError = !descriptionFieldIsValid || movieFormViewModel.descriptionUpdate.trim().isEmpty(),
            label = {
                Text(text = "Description", color = MaterialTheme.colorScheme.primary)
            },
            trailingIcon = {
                if (!descriptionFieldIsValid) {
                    Icon(
                        Icons.Filled.Error,
                        tint = Color.Red,
                        contentDescription = stringResource(id = R.string.this_field_is_required)
                    )
                }
            },
            maxLines = 1,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        )
        if (!descriptionFieldIsValid) {
            Text(
                text = stringResource(R.string.this_field_is_required),
                color = Color.Red,
                fontSize = 16.sp,
                modifier = modifier.padding(start = 8.dp)
            )
        }
    }
}