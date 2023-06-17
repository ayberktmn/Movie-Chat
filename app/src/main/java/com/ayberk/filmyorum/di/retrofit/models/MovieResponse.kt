package com.ayberk.filmyorum.di.retrofit.models

data class MovieResponse(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)