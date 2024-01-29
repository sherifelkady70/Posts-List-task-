package com.route.task_implemented

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.route.task_implemented.api.ApiInterface
import com.route.task_implemented.api.RetrofitInstance
import com.route.task_implemented.databinding.ActivityMainBinding
import com.route.task_implemented.models.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var postsAdapter: PostAdapter
    lateinit var postsList : ArrayList<Posts>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRV()

        val api : ApiInterface= RetrofitInstance.retrofit.create(ApiInterface::class.java)
        val call : Call<ArrayList<Posts>> = api.getData()
        call.enqueue(object : Callback<ArrayList<Posts>>{
            override fun onResponse(
                call: Call<ArrayList<Posts>>,
                response: Response<ArrayList<Posts>>
            ) {
                postsList= response.body()!!
            }

            override fun onFailure(call: Call<ArrayList<Posts>>, t: Throwable) {
                t.message
            }

        })
    }

    private fun prepareRV(){
        postsAdapter=PostAdapter()
        postsAdapter.setPostsList(postsList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = postsAdapter
    }
}