package com.darleyleal.movieapp.ui.components.add_movies_form

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
import com.darleyleal.movieapp.ui.theme.MovieAppTheme
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

@Composable
fun ImageLinkFieldForm(
    movieFormViewModel: MovieFormViewModel,
    modifier: Modifier = Modifier
) {
    MovieAppTheme {
        var imageLinkFieldIsvalid by remember { mutableStateOf(true) }
        TextField(
            value = movieFormViewModel.imageLink,
            onValueChange = {
                movieFormViewModel.imageLink = it
                imageLinkFieldIsvalid = it.trim().isNotEmpty()
            },
            isError = !imageLinkFieldIsvalid ||
                    movieFormViewModel.imageLink.trim().isEmpty(),
            label = {
                Text(text = "Image Link", color = MaterialTheme.colorScheme.primary)
            },
            trailingIcon = {
                if (!imageLinkFieldIsvalid) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        tint = Color.Red,
                        contentDescription = stringResource(R.string.this_field_is_required)
                    )
                }
            },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp, end = 8.dp
                )
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        )

        if (!imageLinkFieldIsvalid) {
            Text(
                text = stringResource(id = R.string.this_field_is_required),
                color = Color.Red,
                fontSize = 16.sp,
                modifier = modifier.padding(start = 8.dp)
            )
        }
    }
}