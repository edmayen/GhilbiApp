package com.example.ghilbiapp.domain.model

import com.example.ghilbiapp.data.db.FavoriteMovieEntity

data class FavoriteMovieModel(
    val id: String,
    val title: String,
    val imageUrl: String,
    val releaseDate: String,
    val rtScore: String
)
