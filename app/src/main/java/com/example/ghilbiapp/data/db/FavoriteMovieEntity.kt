package com.example.ghilbiapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ghilbiapp.domain.model.FavoriteMovieModel
import com.example.ghilbiapp.domain.model.GhibliMovieModel

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val imageUrl: String,
    val releaseDate: String,
    val rtScore: String
)

fun FavoriteMovieEntity.toDomain(): FavoriteMovieModel = FavoriteMovieModel(
    id = this.id,
    title = this.title,
    imageUrl = this.imageUrl,
    releaseDate = this.releaseDate,
    rtScore = this.rtScore
)

fun FavoriteMovieEntity.toGhibliMovie(): GhibliMovieModel = GhibliMovieModel(
    id = this.id,
    title = this.title,
    releaseDate = this.releaseDate,
    image = this.imageUrl
)