package com.example.ghilbiapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ghilbiapp.R

val NunitoFontFamily = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_medium, FontWeight.Medium),
    Font(R.font.nunito_semibold, FontWeight.SemiBold),
    Font(R.font.nunito_bold, FontWeight.Bold)
)


val Typography = Typography(
    // ----------------------------------------------------
    // TÍTULOS (Para tu TopAppBar, "Welcome...", etc.)
    // ----------------------------------------------------
    titleLarge = TextStyle(
        fontFamily = NunitoFontFamily, // Idealmente: GhibliTitleFont
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        // Perfecto para los nombres de las películas en las tarjetas
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),

    // ----------------------------------------------------
    // CUERPO DE TEXTO (Para descripciones, sinopsis, etc.)
    // ----------------------------------------------------
    bodyLarge = TextStyle(
        // El texto general ("Discover the magic of hand-drawn...")
        fontFamily = NunitoFontFamily, // Idealmente: GhibliBodyFont
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        // Para textos un poco más secundarios o largos
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),

    // ----------------------------------------------------
    // ETIQUETAS (Para tu Bottom Navbar, Chips, botones pequeños)
    // ----------------------------------------------------
    labelLarge = TextStyle(
        // Para el texto dentro de botones principales
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelSmall = TextStyle(
        // Perfecto para los textos debajo de los iconos del Bottom Nav ("Library", "Cast")
        // o para el "1988" del año de la película
        fontFamily = NunitoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)