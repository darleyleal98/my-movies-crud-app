package com.darleyleal.movieapp

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.darleyleal.movieapp.navigation.AppNavigation
import com.darleyleal.movieapp.viewmodels.MovieFormViewModel

class MainActivity : ComponentActivity() {

    private val movieFormViewModel: MovieFormViewModel by viewModels()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            requestedOrientation = SCREEN_ORIENTATION_PORTRAIT
            val navController = rememberNavController()

            AppNavigation(
                movieFormViewModel = movieFormViewModel,
                navController = navController
            )
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    content()
}