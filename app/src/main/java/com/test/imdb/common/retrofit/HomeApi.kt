package com.test.imdb.common.retrofit

import com.test.imdb.common.datamodel.Home
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HomeApi {
    @GET("3/movie/now_playing")
    fun getData(@QueryMap params: HashMap<String, String>): Call<Home>
}



//?api_key=38a73d59546aa378980a88b645f487fc&language=en-US&page=1