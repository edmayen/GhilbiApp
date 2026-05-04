package com.example.ghilbiapp.data.api.response

import com.example.ghilbiapp.domain.model.MovieDetailModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(
    val id: String,
    val title: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("original_title_romanised") val originalTitleRomanised: String,
    val image: String,
    @SerialName("movie_banner") val movieBanner: String,
    val description: String,
    val director: String,
    val producer: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("running_time") val runningTime: String,
    @SerialName("rt_score") val rtScore: String
)

fun MovieDetailResponse.toDomain(): MovieDetailModel = MovieDetailModel(
    id = id,
    title = title,
    originalTitle = originalTitle,
    originalTitleRomanised = originalTitleRomanised,
    description = description,
    director = director,
    producer = producer,
    releaseDate = releaseDate,
    runningTime = runningTime,
    rtScore = rtScore,
    image = image,
    movieBanner = movieBanner
)