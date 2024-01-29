package com.route.task_implemented

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
import android.widget.Toast
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
    var postsList  = ArrayList<Posts>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val api : ApiInterface= RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val call : Call<List<Posts>> = api.getData()
       call.enqueue(object : Callback<List<Posts>>{
           override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                   Toast.makeText(this@MainActivity, " Calll ", Toast.LENGTH_LONG).show()
                   if (response.body() != null) {
                       postsList= (response.body() as ArrayList<Posts>?)!!
                       postsAdapter.setList(postsList)
                       Log.d("Call<List<Posts>>","${(response.body() as ArrayList<Posts>).size}")

                   } else {
                       Toast.makeText(this@MainActivity, "noooooo Calll ", Toast.LENGTH_LONG).show()
                   }
               }
           override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                   Toast.makeText(this@MainActivity, "noooooo Calll ", Toast.LENGTH_LONG).show()

           }

       })

        prepareRV()
    }

    private fun prepareRV(){
        postsAdapter=PostAdapter()
        postsAdapter.setList(postsList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = postsAdapter
    }
}