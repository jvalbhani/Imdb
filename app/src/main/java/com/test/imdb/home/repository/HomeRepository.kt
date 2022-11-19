package com.test.imdb.home.repository

import android.content.SharedPreferences
import com.test.imdb.common.datamodel.Home
import com.test.imdb.common.datamodel.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeRepository(
    private val localDataSource: DataSource.Local,
    private val remoteDataSource: DataSource.Remote,
    private val sharedPrefs: SharedPreferences
) : DataSource {

    override fun getData(
        params: HashMap<String, String>,
        callback: DataSource.Callback<Home>,
        forceRemoteFetch: Boolean
    ) {
        if (!forceRemoteFetch) {
            fetchLocally(params, callback)
            return
        }
        fetchRemotely(params, callback)
    }

    override fun getEvents(
        slugs: List<String>,
        callback: DataSource.Callback<List<Movie>>
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            localDataSource.loadMovies(slugs, callback)
        }
    }


    private fun fetchLocally(params: HashMap<String, String>, callback: DataSource.Callback<Home>) {
        GlobalScope.launch {
            localDataSource.loadData(object : DataSource.Callback<Home> {

                override fun onSuccess(responseCode: Int, data: Home?, message: String?) {
                    callback.onSuccess(responseCode, data, message)
                }

                override fun onFailure(errorCode: Int, message: String?) {
                    fetchRemotely(params, callback)
                }
            })
        }
    }

    private fun fetchRemotely(
        params: HashMap<String, String>,
        callback: DataSource.Callback<Home>
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            remoteDataSource.fetchData(params, object : DataSource.Callback<Home> {
                override fun onSuccess(responseCode: Int, data: Home?, message: String?) {
                    if (data != null) {
                        saveAll(data, params["city"])
                        callback.onSuccess(responseCode, data, message)
                        return
                    }
                    callback.onFailure(responseCode, message)
                }

                override fun onFailure(errorCode: Int, message: String?) {
                    callback.onFailure(errorCode, message)
                }
            })
        }
    }

    private fun saveAll(data: Home, city: String?) {
        GlobalScope.launch(Dispatchers.IO) {
            localDataSource.deleteAll()
            localDataSource.save(data)
        }
    }
}