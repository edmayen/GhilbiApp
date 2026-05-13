package com.example.ghilbiapp.di

import android.content.Context
import androidx.room.Room
import com.example.ghilbiapp.data.api.ApiService
import com.example.ghilbiapp.data.db.FavoriteMovieDao
import com.example.ghilbiapp.data.db.GhibliDatabase
import com.example.ghilbiapp.data.repository.GhibliMovieRepositoryImpl
import com.example.ghilbiapp.domain.repository.GhibliMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

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
    @Singleton
    fun providesGhibliDatabase(
        @ApplicationContext context: Context
    ): GhibliDatabase = Room.databaseBuilder(
        context,
        GhibliDatabase::class.java,
        "ghibli_database"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideFavoriteDao(database: GhibliDatabase): FavoriteMovieDao {
        return database.favoriteDao()
    }

    @Provides
    fun providesGhibliMoviesRepository(
        apiService: ApiService,
        favoriteDao: FavoriteMovieDao
    ): GhibliMovieRepository =
        GhibliMovieRepositoryImpl(
            apiService = apiService,
            favoriteDao = favoriteDao
        )
}