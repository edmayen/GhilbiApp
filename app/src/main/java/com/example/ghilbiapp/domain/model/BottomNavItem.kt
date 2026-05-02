package com.example.ghilbiapp.domain.model

import androidx.annotation.DrawableRes
import com.example.ghilbiapp.R

sealed class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    object Library: BottomNavItem("library", "Library", R.drawable.ic_library)
    object Cast: BottomNavItem("cast", "Cast", R.drawable.ic_cast)
    object Favorites: BottomNavItem("favorites", "Favorites", R.drawable.ic_heart)
}