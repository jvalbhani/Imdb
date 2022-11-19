package com.test.imdb.common.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "home")
data class RoomHome(
    @PrimaryKey val id: Long = 0,
    val tags: List<String>?,
    val page: Int,
)

data class RoomHomeWithMovie(
    @Embedded val home: RoomHome,
    @Embedded val dates: RoomDates,
    @Relation(
        entityColumn = "homeId",
        parentColumn = "id",
        entity = RoomMovieList::class
    ) val result: RoomMovieList
)

@Entity(tableName = "movieList")
data class RoomMovieList(
    @PrimaryKey val homeId: Long = 0,
    val list: List<RoomMovie>?
)

@Entity(tableName = "movie", primaryKeys = ["id"])
data class RoomMovie(
    val adult: Boolean,
    val id: Int,
    val overview: String?,
    val popularity: Int,
    val title: String? = null,
    val video: Boolean,
    val backdropPath: String?,
    val genreIds: HashSet<Int>?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double,
    val voteCount: Int
)

@Entity(tableName = "dates", primaryKeys = ["dateId"])
data class RoomDates(
    @PrimaryKey val dateId: Long = 0,
    val maximum: String?,
    val minimum: String?
)