package com.example.ghilbiapp.data.api

import com.example.ghilbiapp.data.api.response.MovieResponse
import retrofit2.http.GET


interface ApiService {

    @GET("films")
    suspend fun fetchMovies(): List<MovieResponse>
}