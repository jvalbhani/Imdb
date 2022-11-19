package com.test.imdb.common.datamodel

import com.google.gson.annotations.SerializedName

data class Home(
    val tags: List<String>?,
    val dates: Dates,
    val page: Int,
    val result: List<Movie>?,
)

data class Movie(
    val adult: Boolean,
    val id: Int,
    val overview: String?,
    val popularity: Int,
    val title: String? = null,
    val video: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genreIds: HashSet<Int>?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)


data class Dates(
    val maximum: String?,
    val minimum: String?
)