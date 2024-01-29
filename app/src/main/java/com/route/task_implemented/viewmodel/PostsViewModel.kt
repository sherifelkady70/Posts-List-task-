package com.route.task_implemented.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.task_implemented.api.ApiInterface
import com.route.task_implemented.api.RetrofitInstance
import com.route.task_implemented.models.Posts
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class PostsViewModel  : ViewModel() {

    val postLiveData : MutableLiveData<List<Posts>> = MutableLiveData()

    fun getPostsData(){
        val api : ApiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val call = api.getData()
        call.enqueue(object : retrofit2.Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                postLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
               t.message
            }

        })
    }
    
    fun getPostLiveData() : LiveData<List<Posts>>{
        return postLiveData
    }
}