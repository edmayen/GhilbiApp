package com.example.ghilbiapp.utils

import com.example.ghilbiapp.domain.model.GhibliMovieModel

object MockMovies {

    val mockMovies = listOf(
        GhibliMovieModel(
            id = "2baf70d1-42bb-4437-b551-e5fed5a87abe",
            title = "Castle in the Sky",
            releaseDate = "1986",
            image = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/npOnzAbLh6VOIu3naU5QaEcTepo.jpg"
        ),
        GhibliMovieModel(
            id = "12cfb892-aac0-4c5b-94af-521852e46d6a",
            title = "Grave of the Fireflies",
            releaseDate = "1988",
            image = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/qG3RYlIVpTYclR9TYIsy8p7m7AT.jpg"
        ),
        GhibliMovieModel(
            id = "58611129-2dbc-4a81-a72f-77ddfc1b1b49",
            title = "My Neighbor Totoro",
            releaseDate = "1988",
            image = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/rtGDOeG9LzoerkDGZF9dnVeLppL.jpg"
        ),
        GhibliMovieModel(
            id = "ea660b10-85c4-4ae3-8a5f-41cea3648e3e",
            title = "Kiki's Delivery Service",
            releaseDate = "1989",
            image = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/7nO5DUMnGUuXrA4r2h6ESOKQRrx.jpg"
        ),
        GhibliMovieModel(
            id = "4e236f34-b981-41c3-8c65-f8c9000b94e7",
            title = "Only Yesterday",
            releaseDate = "1991",
            image = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/xjJU6rwzLX7Jk8HFQfVW6H5guMC.jpg"
        )
    )
}