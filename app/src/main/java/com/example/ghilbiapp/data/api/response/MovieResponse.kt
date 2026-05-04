package com.example.ghilbiapp.data.api.response

import com.example.ghilbiapp.domain.model.GhibliMovieModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse (
    val id: String,
    val title: String,
    @SerialName("original_title") val originalTitle: String,
    val description: String,
    val image: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("running_time") val runningTime: String
)

fun MovieResponse.toDomain(): GhibliMovieModel = GhibliMovieModel(
    id = id,
    title = title,
    releaseDate = releaseDate,
    image = image
)