package com.example.ghilbiapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val imageUrl: String,
    val releaseDate: String,
    val rtScore: String
)
