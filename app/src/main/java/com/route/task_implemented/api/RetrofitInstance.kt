package com.route.task_implemented.api

import com.route.task_implemented.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    lateinit var retrofit: Retrofit
   fun getRetrofitInstance() : Retrofit {
       retrofit = Retrofit.Builder()
           .baseUrl(Constants.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
       return retrofit
   }
}
