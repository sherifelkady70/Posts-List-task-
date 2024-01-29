package com.route.task_implemented.api

import com.route.task_implemented.models.Posts
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/posts")
    fun getData() : Call<List<Posts>>
}