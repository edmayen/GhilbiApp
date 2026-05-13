package com.example.ghilbiapp.domain.model

import com.example.ghilbiapp.data.db.FavoriteMovieEntity
import com.example.ghilbiapp.view.moviedetail.CharactersUiState

data class MovieDetailModel(
    val id: String,
    val title: String,
    val originalTitle: String,
    val originalTitleRomanised: String,
    val description: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val runningTime: String,
    val rtScore: String,
    val image: String,
    val movieBanner: String,
    val characterUrls: List<String>,
    val charactersState: CharactersUiState = CharactersUiState.Loading,
    val isFavorite: Boolean = false
)

fun MovieDetailModel.toEntity() = FavoriteMovieEntity(
    id = id,
    title = title,
    imageUrl = image,
    releaseDate = releaseDate,
    rtScore = rtScore
)