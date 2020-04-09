package com.example.rxjavatestingfeatures.data.models.toprated

import com.example.rxjavatestingfeatures.data.models.toprated.Movie

data class Movies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)