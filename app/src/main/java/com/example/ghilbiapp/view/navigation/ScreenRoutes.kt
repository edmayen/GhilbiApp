package com.example.ghilbiapp.view.navigation

import kotlinx.serialization.Serializable

@Serializable
object LibraryRoute

@Serializable
object FavoritesRoute

@Serializable
data class MovieDetailRoute(val movieId: String)