package com.test.imdb.home.repository.local

import com.test.imdb.home.repository.DataSource
import com.test.imdb.common.datamodel.Home
import com.test.imdb.common.datamodel.Movie
import com.test.imdb.common.room.dao.HomeDao

class LocalDataSource(private val homeDao: HomeDao) : DataSource.Local {

    override suspend fun loadData(callback: DataSource.Callback<Home>) {
//        val data = homeDao.loadAll().map { it.map() }
//        if (data.isNullOrEmpty()) {
//            callback.onFailure(-1, "Failed to load data from cache")
//            return
//        }
//        callback.onSuccess(0, data[0])
    }

    override suspend fun save(data: Home) {
//        homeDao.saveAll(data.map())
    }

    override suspend fun deleteAll() {
        homeDao.deleteAll()
    }

    override suspend fun loadMovies(
        slugs: List<String>,
        callback: DataSource.Callback<List<Movie>>
    ) {
//        val data = homeDao.loadEvents(slugs)
//        callback.onSuccess(0, data.map { it.map() })
    }
}