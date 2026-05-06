package com.example.ghilbiapp.di

import com.example.ghilbiapp.data.api.ApiService
import com.example.ghilbiapp.data.repository.GhibliMovieRepositoryImpl
import com.example.ghilbiapp.domain.repository.GhibliMovieRepository
import com.example.ghilbiapp.domain.usecases.GetCharactersUseCase
import com.example.ghilbiapp.domain.usecases.MovieDetailUseCase
import com.example.ghilbiapp.domain.usecases.ObserveFavoriteStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    fun provideRetrofit(json: Json): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://ghibliapi.vercel.app/")
            .addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .build()

    @Provides
    fun provideJson(): Json =
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

    @Provides
    fun providesGhibliMoviesRepository(apiService: ApiService): GhibliMovieRepository =
        GhibliMovieRepositoryImpl(apiService)

    @Provides
    fun providesMovieDetailUseCase(movieRepository: GhibliMovieRepository): MovieDetailUseCase =
        MovieDetailUseCase(movieRepository)

    @Provides
    fun providesGetCharactersUseCase(movieRepository: GhibliMovieRepository): GetCharactersUseCase =
        GetCharactersUseCase(movieRepository)

    @Provides
    fun providesObserveFavoriteStatusUseCase(): ObserveFavoriteStatusUseCase =
        ObserveFavoriteStatusUseCase()

}