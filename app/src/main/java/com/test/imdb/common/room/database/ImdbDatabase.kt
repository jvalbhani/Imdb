package com.test.imdb.common.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.imdb.common.room.dao.HomeDao
import com.test.imdb.common.room.entity.*

@Database(
    entities = [RoomHome::class, RoomMovieList::class, RoomMovie::class],
    version = 1,
    exportSchema = false
)
abstract class ImdbDatabase : RoomDatabase() {
    abstract fun getHomeDao(): HomeDao
}