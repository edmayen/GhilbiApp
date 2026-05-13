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

import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ghilbiapp.view.favorites.FavoritesScreen
import com.example.ghilbiapp.view.navigation.FavoritesRoute

import androidx.navigation.NavDestination.Companion.hasRoute

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            val isDetail = destination?.hasRoute<MovieDetailRoute>() == true
            CustomTopAppBar(
                title = if (isDetail) "Movie Details" else "Ghibli Theater",
                isVisibleNavIcon = isDetail,
                onBackClicked = { navController.popBackStack() }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = when {
                    destination?.hasRoute<LibraryRoute>() == true -> "library"
                    destination?.hasRoute<FavoritesRoute>() == true -> "favorites"
                    else -> null
                },
                onNavigate = { route ->
                    when(route) {
                        "library" -> navController.navigate(LibraryRoute) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        "favorites" -> navController.navigate(FavoritesRoute) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
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

                composable<FavoritesRoute> {
                    FavoritesScreen(
                        onMovieClick = { movieId ->
                            navController.navigate(MovieDetailRoute(movieId = movieId))
                        }
                    )
                }

                composable<MovieDetailRoute> { backStackEntry ->
                    MovieDetailScreen()
                }
            }
        },
    )
}