package com.test.imdb.home.repository

import com.test.imdb.common.datamodel.Home
import com.test.imdb.common.datamodel.Movie

interface DataSource {
    fun getData(
        params: HashMap<String, String>,
        callback: Callback<Home>,
        forceRemoteFetch: Boolean = false
    )

    fun getEvents(slugs: List<String>, callback: Callback<List<Movie>>)

    interface Remote {
        suspend fun fetchData(params: HashMap<String, String>, callback: Callback<Home>)
    }

    interface Local {
        suspend fun loadData(callback: Callback<Home>)
        suspend fun save(data: Home)
        suspend fun deleteAll()
        suspend fun loadMovies(slugs: List<String>, callback: Callback<List<Movie>>)
    }

    interface Callback<T> {
        fun onSuccess(responseCode: Int, data: T?, message: String? = null)

        fun onFailure(errorCode: Int, message: String? = null)
    }
}