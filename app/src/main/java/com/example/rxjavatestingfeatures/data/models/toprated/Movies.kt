package com.example.rxjavatestingfeatures.data.models.toprated

data class Movies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)