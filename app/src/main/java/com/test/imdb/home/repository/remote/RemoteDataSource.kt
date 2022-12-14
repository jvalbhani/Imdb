package com.test.imdb.home.repository.remote

import android.util.Log
import com.test.imdb.home.repository.DataSource
import com.test.imdb.common.datamodel.Home
import com.test.imdb.common.retrofit.HomeApi
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RemoteDataSource(private val retrofit: Retrofit) : DataSource.Remote {

    override suspend fun fetchData(
        params: HashMap<String, String>,
        callback: DataSource.Callback<Home>
    ) {
        try {
            val service = retrofit.create(HomeApi::class.java)
            val request = service.getData(params)
            request.enqueue(object : Callback<Home> {
                override fun onFailure(call: retrofit2.Call<Home>, t: Throwable) {
                    Log.e("RemoteDataSource", "onFailure: ${t.message}")
                    callback.onFailure(-1, t.message)
                }

                override fun onResponse(call: retrofit2.Call<Home>, response: Response<Home>) {
                    Log.d("RemoteDataSource", "onSuccess: ${response.body()}")
                    callback.onSuccess(response.code(), response.body(), response.message())
                }
            })
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "exception: ${e.message}")
            callback.onFailure(-1, e.message)
        }
    }
}