package com.example.ghilbiapp.view.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ghilbiapp.view.core.BottomNavigationBar
import com.example.ghilbiapp.view.core.CustomTopAppBar
import com.example.ghilbiapp.view.library.LibraryScreen
import com.example.ghilbiapp.view.moviedetail.MovieDetailScreen
import com.example.ghilbiapp.view.navigation.LibraryRoute
import com.example.ghilbiapp.view.navigation.MovieDetailRoute

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = "Ghibli Theater",
                isVisibleNavIcon = true,
                onBackClicked = {}
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "library",
                onNavigate = { route ->
                    
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = LibraryRoute,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<LibraryRoute> {
                    LibraryScreen(
                        onMovieClick = { movieId ->
                            navController.navigate(MovieDetailRoute(movieId = movieId))
                        }
                    )
                }

                composable<MovieDetailRoute> { backStackEntry ->
                    // ¡No necesitas extraer el ID aquí en la UI!
                    // El ViewModel lo hará por ti en el siguiente paso.
                    MovieDetailScreen(
                        // Hilt inyectará el ViewModel automáticamente aquí
                    )
                }
            }
        },
    )
}