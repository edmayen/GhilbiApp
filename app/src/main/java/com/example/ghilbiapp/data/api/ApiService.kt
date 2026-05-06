package com.example.ghilbiapp.data.api

import com.example.ghilbiapp.data.api.response.CharacterResponse
import com.example.ghilbiapp.data.api.response.MovieDetailResponse
import com.example.ghilbiapp.data.api.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("films")
    suspend fun fetchMovies(): List<MovieResponse>

    @GET("films/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: String
    ): MovieDetailResponse

    @GET("people/{id}")
    suspend fun getCharacterById(
        @Path("id") id: String
    ): CharacterResponse
}