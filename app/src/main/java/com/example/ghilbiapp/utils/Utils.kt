package com.example.ghilbiapp.utils

object Utils {

    fun String.toFormattedDuration(): String {
        val totalMinutes = this.toIntOrNull() ?: return "0m"

        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60

        return when {
            hours > 0 && minutes > 0 -> "${hours}h ${minutes}m"
            hours > 0 -> "${hours}h"
            else -> "${minutes}m"
        }
    }

    fun String.toFormattedScore(): String {
        val score = this.toDoubleOrNull() ?: return "N/A"
        val scoreOutOfTen = score / 10.0
        val formattedNumber = if (scoreOutOfTen % 1.0 == 0.0) {
            scoreOutOfTen.toInt().toString()
        } else {
            scoreOutOfTen.toString()
        }
        return "$formattedNumber/10"
    }
}