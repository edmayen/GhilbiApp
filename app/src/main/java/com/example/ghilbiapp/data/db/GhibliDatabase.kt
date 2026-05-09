package com.example.ghilbiapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteMovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GhibliDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteMovieDao
}